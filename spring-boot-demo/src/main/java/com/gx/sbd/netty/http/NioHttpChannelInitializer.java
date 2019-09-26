package com.gx.sbd.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @ClassName : NioHttpChannelInitializer
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/26 10:02
 * @Version : 1.0
 */
public class NioHttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast("decoder", new HttpRequestDecoder())   // 1
                .addLast("encoder", new HttpResponseEncoder())  // 2
                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3
                .addLast("handler", new HttpHandler());
    }

}
