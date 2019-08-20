package com.gx.demo.controller;

import com.gx.demo.service.imp.PrestoDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : DemoController
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 10:57
 * @Version : 1.0
 */
@RestController
public class DemoController {

    @Autowired
    private PrestoDemoServiceImpl prestoDemoService;

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/presto/demo",method = {RequestMethod.GET,RequestMethod.POST})
    public Object prestoDemo(){
        return prestoDemoService.test1();
    }


    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/presto/demo2",method = {RequestMethod.GET,RequestMethod.POST})
    public Object prestoDemo2(){
        return prestoDemoService.select1();
    }

}
