package com.gx.sbd.controllers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gx.demo.CommonBaseConstants;
import com.gx.demo.excel.ExcelDTO;
import com.gx.demo.excel.ExcelUtil;
import com.gx.demo.utils.BaseResponse;
import com.gx.sbd.annotation.NeedLogin;
import com.gx.sbd.annotation.ServiceLimit;
import com.gx.sbd.servers.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : DemoController
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/22 14:03
 * @Version : 1.0
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @Autowired
    private DemoService demoService;



    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Object paramTest(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        BaseResponse response = BaseResponse.newInstance();
       Map<String,List<List<String>>> map = ExcelUtil.createExcelReader()
                .setFileName(file.getName())
                .setFileInputStream((FileInputStream) file.getInputStream())
                .skipFirstLine()
                .read();
       response.success().put(CommonBaseConstants.DATE_KEY,map);
        return response.toResponseMap();
    }

    /**
     * 多文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping("/upload2")
    public Object upload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
        BaseResponse response = BaseResponse.newInstance();
        List<Map<String,List<List<String>>>> rst = Lists.newLinkedList();
        for (MultipartFile file : files){
            Map<String,List<List<String>>> map = ExcelUtil.createExcelReader()
                    .setFileName(file.getName())
                    .setFileInputStream((FileInputStream) file.getInputStream())
                    .skipFirstLine()
                    .read();

            rst.add(map);
        }
        response.success().put(CommonBaseConstants.DATE_KEY,rst);
        return response.toResponseMap();
    }



    /**
     * excel 文件下载
     * @param request
     * @param response
     */
    @GetMapping("/down")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "测试.xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName,"UTF-8"));
        OutputStream outputStream = response.getOutputStream();

        ExcelDTO dto = new ExcelDTO();
        dto.setFileName(fileName);
        String sheetName = "zs1";
        List<String> titles = Lists.newLinkedList();
        titles.add("编号");
        titles.add("名称");
        Map<String,List<String>> sheetAndTitles = Maps.newHashMap();
        sheetAndTitles.put(sheetName,titles);
        dto.setSheets(sheetAndTitles);

        List<List<String>> datas = Lists.newLinkedList();
        for (int i = 0; i < 100; i++) {
            List<String> list = Lists.newLinkedList();
            list.add("ASSSSSS"+i);
            list.add("名字是:"+i);
            datas.add(list);
        }

        Map<String,List<List<String>>> sheetAndDatas = Maps.newHashMap();
        sheetAndDatas.put(sheetName,datas);
        dto.setDatas(sheetAndDatas);

        ExcelUtil.createExcelWriter()
                .setFileName(fileName)
                .setDatas(dto)
                .setOutputStream(outputStream)
                .write();
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 测试
     * @return
     */
    @PostMapping("/dt1")
    public Object demo1(){
        return demoService.demo1();
    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/gxd")
    public Object demo2(){
        BaseResponse response = BaseResponse.newInstance();
        response.success().put(CommonBaseConstants.DATE_KEY,new Date());
        return response;
    }


    /**
     * 限流测试
     * @return
     */
    @ServiceLimit(limitType = ServiceLimit.LimitType.IP)
    @GetMapping("/limit-demo")
    public Object limitDemo(@RequestParam("name") String name){
        BaseResponse response = BaseResponse.newInstance();
        response.success().put(CommonBaseConstants.DATE_KEY,name +" || "+System.currentTimeMillis());
        return response;
    }

    @NeedLogin
    @GetMapping("/need-login")
    public Object loginAnnotationsTest(){
        BaseResponse response = BaseResponse.newInstance();
        response.success();
        return response;
    }


    @GetMapping("/no-need-login")
    public Object NologinAnnotationsTest(){
        BaseResponse response = BaseResponse.newInstance();
        response.success();
        return response;
    }
}
