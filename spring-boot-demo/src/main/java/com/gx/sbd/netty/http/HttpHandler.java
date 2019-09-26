package com.gx.sbd.netty.http;

import com.alibaba.fastjson.JSON;
import com.gx.demo.utils.BaseResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : HttpHandler
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/26 10:05
 * @Version : 1.0
 */
class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

        private AsciiString contentType = HttpHeaderValues.APPLICATION_JSON;

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

                logger.info("request handler: {}" , msg.getClass().getName());

                logger.info("request method :{}",msg.method());

                logger.info("request url :{}",msg.uri());
                BaseResponse rest = BaseResponse.newInstance();
                rest.success("successful request ！！");

                DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.wrappedBuffer(JSON.toJSONString(rest).getBytes()));

                HttpHeaders heads = response.headers();
                heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
                heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
                heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);


                ctx.write(response);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                logger.info("channelReadComplete ....");
                super.channelReadComplete(ctx);
                ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                logger.info("exceptionCaught......");
                if(null != cause) cause.printStackTrace();
                if(null != ctx) ctx.close();
        }
}
