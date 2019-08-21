package com.gx.sbd.hadoop;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.api.records.*;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName : YarnLogAdaptor
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/7/26 11:00
 * @Version : 1.0
 */
public class YarnLogAdaptor {

    /**
     * 判断 application 是否正在执行
     * @param state
     * @return
     */
    public static boolean isRunning(YarnApplicationState state) {
        return !(YarnApplicationState.FAILED.equals(state) || YarnApplicationState.KILLED.equals(state) || YarnApplicationState.FINISHED.equals(state));
    }

    /**
     * 根据 applicationId 获取 log 日志文件 路径
     * @param appId
     * @throws Exception
     */
    public static void getApplicationLogsByApplicationId(String appId) throws Exception{
        if(StringUtils.isBlank(appId)){
            return;
        }

        YarnClient yarnClient = YarnClient.createYarnClient();

        yarnClient.init(new YarnConfiguration());

        yarnClient.start();

        try {

            ApplicationId applicationId = ConverterUtils.toApplicationId(appId);

            ApplicationReport applicationReport = yarnClient.getApplicationReport(applicationId);

            YarnApplicationState state = applicationReport.getYarnApplicationState();

            if (isRunning(state)) {
                ApplicationAttemptReport attemptReport = yarnClient.getApplicationAttemptReport(applicationReport.getCurrentApplicationAttemptId());
                ContainerReport driverReport = yarnClient.getContainerReport(attemptReport.getAMContainerId());

                System.out.println(driverReport);

                String logUrl = driverReport.getLogUrl();

                System.out.println(logUrl);
            }else {
                System.out.println("job is running");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            yarnClient.close();
        }
    }


    public static void yarnTest() throws Exception{

        Configuration configuration = new Configuration();
        configuration.addResource(new Path("D:\\yarn-site.xml"));
        configuration.addResource(new Path("D:\\core-site.xml"));
        configuration.addResource(new Path("D:\\hdfs-site.xml"));
        YarnClient yarnClient = YarnClient.createYarnClient();
//        YarnConfiguration yarnConfiguration = new YarnConfiguration();
//        yarnConfiguration.addResource(new Path("D:\\yarn-site.xml"));
        yarnClient.init(configuration);
        yarnClient.start();
        System.out.println("开始");
        try {

            List<ApplicationReport> applicationReportList = yarnClient.getApplications();

            if (!CollectionUtils.isEmpty(applicationReportList)) {
                for (ApplicationReport report : applicationReportList) {
                    YarnApplicationState state = report.getYarnApplicationState();
                    System.out.println("state: "+state);
                    ApplicationId applicationId = report.getApplicationId();
                    System.out.println("applicationId: "+applicationId);
                    ApplicationAttemptId attemptId = report.getCurrentApplicationAttemptId();
                    System.out.println("attemptId: "+attemptId);
                    ApplicationAttemptReport attemptReport = yarnClient.getApplicationAttemptReport(attemptId);
                    ContainerReport driverReport = yarnClient.getContainerReport(attemptReport.getAMContainerId());
                    System.out.println("driver: "+driverReport);
                    String logUrl = driverReport.getLogUrl();
                    System.out.println("logUrl: "+logUrl);

                    System.out.println("---------------------分割线------------------------");
                }
            }else {
                System.out.println("没有查询到任何程序");
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("************");
        } finally {
            yarnClient.close();
        }
    }

    /**
     * mian函数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {


        String appid = "attemptReport";
        yarnTest();

    }
}
