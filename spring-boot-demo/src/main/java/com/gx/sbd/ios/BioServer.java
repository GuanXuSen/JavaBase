package com.gx.sbd.ios;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : BioServer
 * @Description :TOO 全阻塞
 * @Author : gx
 * @Date : 2019/9/25 15:06
 * @Version : 1.0
 */
public class BioServer {
    // 接入计数
    static AtomicInteger counter = new AtomicInteger(0);
    // 格式化时间
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    static String time() {
        return sdf.format(new Date());
    }

    /**
     * 线程执行
     * @param s
     */
    static void processWithNewThread(Socket s) {
        Runnable run = () -> {
            InetSocketAddress rsa = (InetSocketAddress)s.getRemoteSocketAddress();
            InputStream ips = null;
            OutputStream ost = null;
            System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + counter.incrementAndGet());
            try {
                 ips = s.getInputStream();
                 ost = s.getOutputStream();
                 PrintWriter printWriter = new PrintWriter(ost);
                 s.setKeepAlive(true);
                while (true){
                    String result = readBytes(ips);
                    if(StringUtils.isNotBlank(result)){
                        System.out.println("服务器收到："+result);
                        String msg = "servers get msg ok : "+result;
                        printWriter.write(msg);
                        printWriter.flush();
                        System.out.println("向客户端发送："+msg);
                    }
                     if ("end".equals(result)) {
                        break;
                    }
                    Thread.sleep(10);
//                    printWriter.write("💗💗💗💗💗💗💗💗💗💗");
//                    printWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                closeAll(ost,ips,s);
            }
        };
        new Thread(run).start();
    }

    static void closeAll(OutputStream ost,InputStream ips,Socket s){
        if(null != ost){
            try {
                ost.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != ips){
            try {
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != s){
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取数据
     * @param is
     * @return
     * @throws Exception
     */
    static String readBytes(InputStream is) throws Exception {
//        StringBuilder msg = new StringBuilder();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//        String line = "";
//        is.available()
//        while ((line = br.readLine()) != null){
//            msg.append(line);
//        }
//        return msg.toString();

//        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = -1;
//        while ((len = is.read(buffer)) != -1) {
//            outSteam.write(buffer, 0, len);
//        }
//        return outSteam.toString();

        int count = is.available();
        byte[] b = new byte[count];
        is.read(b);
        return new String(b);
    }


    /**
     * 启动
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("localhost", 8080));
            System.out.println("BioServer start .......");
            while (true) {
                Socket s = ss.accept();
                processWithNewThread(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
