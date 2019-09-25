package com.gx.sbd.ios;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @ClassName : Client
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/25 15:11
 * @Version : 1.0
 */
public class Client {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 1; i++) {
                Socket s = new Socket();
                s.connect(new InetSocketAddress("localhost", 10086));
                processWithNewThread(s, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void processWithNewThread(Socket s, int i) {
        Runnable run = () -> {
            InputStream ips = null;
            OutputStream out = null;
            try {
                s.setKeepAlive(true);
                out =  s.getOutputStream();
                ips = s.getInputStream();
                while (true){
                    //睡眠1秒，让服务器端把数据读完
                    Thread.sleep(10);
                    String result = readBytes(ips);
                    if(StringUtils.isNotBlank(result)){
                        System.out.println(result);
                        if("end".startsWith(result)){
                            break;
                        }else {
                            String msg = "zhangsan " + i +" : " +result;
                            PrintWriter writer = new PrintWriter(out);
                            writer.write(msg);
                            writer.flush();
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                closeAll(out,ips,s);
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
//        int count = 0;
//        byte[] bytes = new byte[1024];
//        String msg = "";
//        while ((count = is.read(bytes)) > -1) {
//            msg = new String(bytes);
//        }
//        StringBuilder msg = new StringBuilder();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//        String line;
//        while ((line = br.readLine()) != null){
//            msg.append(line);
//        }
//        return "收到消息 : " + msg.toString();

        int count = is.available();
        byte[] b = new byte[count];
        is.read(b);
        return new String(b);
    }



}
