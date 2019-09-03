package com.gx.sbd.controllers;

import com.gx.sbd.servers.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

}
