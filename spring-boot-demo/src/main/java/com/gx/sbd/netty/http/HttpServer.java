package com.gx.sbd.netty.http;

import com.gx.sbd.netty.websocket.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : HttpServer
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/26 9:58
 * @Version : 1.0
 */
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static void start(){
        logger.info("正在启动 http 服务器");
        ServerBootstrap bootstrap = null;
        NioEventLoopGroup group = null;
        try {
            bootstrap = new ServerBootstrap();
            group = new NioEventLoopGroup();

            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NioHttpChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

            Channel channel = bootstrap.bind(8888).sync().channel();

            logger.info("http 服务器启动成功：{}",channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("运行出错：",e);
        }finally {
            group.shutdownGracefully();
            logger.info("http 服务器已关闭");
        }
    }

    public static void main(String[] args) {
        HttpServer.start();
    }
}
