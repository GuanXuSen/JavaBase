package com.gx.sbd.controllers.websockets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName : NettyWebSocketController
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/12 15:50
 * @Version : 1.0
 */
@Controller
public class NettyWebSocketController {

    @GetMapping("/demo-netty-wbs")
    public String wbs(){
        return "websocket";
    }
}
