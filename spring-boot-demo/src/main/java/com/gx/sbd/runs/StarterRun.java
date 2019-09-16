package com.gx.sbd.runs;

import com.gx.sbd.netty.websocket.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName : StarterRun
 * @Description :TOO 启动执行
 * @Author : gx
 * @Date : 2019/9/12 15:59
 * @Version : 1.0
 */
@Component
public class StarterRun implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StarterRun.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("websocket 开始启动");
        NettyServer.start();
    }
}
