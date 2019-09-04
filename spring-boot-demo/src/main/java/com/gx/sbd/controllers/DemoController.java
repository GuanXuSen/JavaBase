package com.gx.sbd.controllers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gx.demo.excel.ExcelDTO;
import com.gx.demo.excel.ExcelUtil;
import com.gx.sbd.servers.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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

    @PostMapping("/dt1")
    public Object demo1(){
        return demoService.demo1();
    }

    @PostMapping("/paramTest")
    public Object paramTest(@RequestParam("pam") DemoParam param, @RequestParam("file")MultipartFile file, HttpServletRequest request){

        System.out.println(param.toString());

        System.out.println(file.getName());

        return "ok";
    }

    @GetMapping("/down1")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
