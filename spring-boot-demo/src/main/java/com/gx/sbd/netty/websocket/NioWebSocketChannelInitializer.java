package com.gx.sbd.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @ClassName : NioWebSocketChannelInitializer
 * @Description :TOO 自定义管道逻辑
 * @Author : gx
 * @Date : 2019/9/12 15:29
 * @Version : 1.0
 */
public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        //设置log监听器，并且日志级别为debug，方便观察运行流程
        ch.pipeline().addLast("logging",new LoggingHandler("DEBUG"))
        //设置解码器
        .addLast("http-codec",new HttpServerCodec())
        //聚合器，使用websocket会用到
        .addLast("aggregator",new HttpObjectAggregator(65536))
        //用于大数据的分区传输
        .addLast("http-chunked",new ChunkedWriteHandler())
        //自定义的业务处理
        .addLast("handler",new NioWebSocketHandler());
    }
}
