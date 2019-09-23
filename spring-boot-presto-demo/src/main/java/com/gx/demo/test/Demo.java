package com.gx.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @ClassName : Demo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 11:49
 * @Version : 1.0
 */
public class Demo {


    /**
     * mysql jdbc
     * @throws Exception
     */
    private static void getPerstoMysql() throws Exception{
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");

        Connection connection = DriverManager.getConnection(
                "jdbc:presto://192.168.155.228:8080/mysql/base_demo",
                "mysql",
                null);

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("show tables");

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

        rs.close();
        connection.close();
    }

    /**
     * mysql jdbc
     * @throws Exception
     */
    private static void getPerstoHive() throws Exception{
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");

        Connection connection = DriverManager.getConnection(
                "jdbc:presto://192.168.154.80:8080/hive/gxdatabase",
                "hive",
                null);

        Statement stmt = connection.createStatement();

//        ResultSet rs = stmt.executeQuery("show tables");
//
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }

        ResultSet stures = stmt.executeQuery("select * from student");

        while (stures.next()) {
            System.out.println(stures.getString(1)+" ** "+stures.getString(2)+" ** "+stures.getString(2));

        }

        stures.close();
       // rs.close();
        connection.close();
    }




    public static void main(String[] args) throws Exception {
        getPerstoHive();
    }
}
