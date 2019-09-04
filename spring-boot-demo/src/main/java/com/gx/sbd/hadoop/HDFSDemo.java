package com.gx.sbd.hadoop;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * @ClassName : HDFSDemo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/7/12 11:28
 * @Version : 1.0
 */
public class HDFSDemo {

//    private static final String HDFS_PATH = "hdfs://192.168.152.98:9000";
//
//    private static final Logger logger = LoggerFactory.getLogger(HDFSDemo.class);
//    /**
//     * 获取 fileSystem
//     * @return
//     */
//    public static FileSystem getFileSystem(){
//        try {
//            Configuration cf = new Configuration();
//            cf.set("fs.defaultFS",HDFS_PATH);
//            return FileSystem.get(new URI(HDFS_PATH),cf,"root");
//        }catch (Exception e){
//            logger.error("获取失败",e);
//        }
//        return null;
//    }
//
//
//
//    /**
//     * 关闭
//     * @param fileSystem
//     */
//    public static void closeFileSystem(FileSystem fileSystem)  {
//        try{
//            if(null != fileSystem){
//                fileSystem.close();
//            }
//        }catch (Exception e){
//            logger.error("关闭失败",e);
//        }
//
//    }
//
//    /**
//     * 测试
//     * @param args
//     */
//    public static void main(String[] args) {
//
//    }

}
