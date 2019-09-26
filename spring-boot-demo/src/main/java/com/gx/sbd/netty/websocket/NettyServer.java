package com.gx.sbd.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : NettyServer
 * @Description :TOO websocket 服务启动类
 * @Author : gx
 * @Date : 2019/9/12 15:17
 * @Version : 1.0
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     *  启动
     */
    public static void start(){
        logger.info("正在启动websocket服务器");
        NioEventLoopGroup boss=new NioEventLoopGroup();
        NioEventLoopGroup work=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NioWebSocketChannelInitializer());

            Channel channel = bootstrap.bind(8888).sync().channel();
            logger.info("webSocket服务器启动成功：{}",channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("运行出错：",e);
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
            logger.info("websocket服务器已关闭");
        }
    }

    public static void main(String[] args) {
        start();
    }
}
