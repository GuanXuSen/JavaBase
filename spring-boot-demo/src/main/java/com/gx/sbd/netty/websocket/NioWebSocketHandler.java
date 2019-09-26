package com.gx.sbd.netty.websocket;

import com.alibaba.fastjson.JSON;
import com.gx.demo.utils.BaseResponse;
import com.gx.sbd.netty.websocket.entity.MessageEntity;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AsciiString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : NioWebSocketHandler
 * @Description :TOO 消息处理
 * @Author : gx
 * @Date : 2019/9/12 15:35
 * @Version : 1.0
 */
public class NioWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private final Logger logger = LoggerFactory.getLogger(NioWebSocketHandler.class);

    /**
     * 处理 websocket 消息
     */
    private WebSocketServerHandshaker handshaker;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("收到消息：{}",msg);

        if (msg instanceof FullHttpRequest){
            //以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }else if (msg instanceof WebSocketFrame){
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     *  新增链接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加连接
        logger.info("客户端加入连接：{}",ctx.channel());
        ChannelSupervise.addChannel(ctx.channel());
    }

    /**
     * 断开链接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接
        logger.info("客户端断开连接：{}",ctx.channel());
        ChannelSupervise.removeChannel(ctx.channel());
    }

    /**
     * 读取
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("server ：channelReadComplete ....");
        ctx.flush();
    }

    /**
     * 处理websocket类型消息
     * @param ctx
     * @param frame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write( new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            logger.info("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        logger.info("服务端收到：" + request);
        TextWebSocketFrame tws = new TextWebSocketFrame(ctx.channel().id() + " ：" + request);
        try{
            MessageEntity msg = JSON.parseObject(request,MessageEntity.class);
            // 群发
            if(StringUtils.isNotBlank(msg.getSendUserId())){
                ChannelSupervise.addChannelName(msg.getSendUserId(),ctx.channel().id().asShortText());
            }
            if (StringUtils.isNotBlank(msg.getToSendUserId())) {
                ChannelSupervise.sendByName(msg.getToSendUserId(),tws);
            }else {
                ChannelSupervise.send2All(tws);
            }

        }catch (Exception e){
            logger.error("非法消息",e);
            // 返回【谁发的发给谁】
             tws = new TextWebSocketFrame(ctx.channel().id() + " ：非法信息" );
             ctx.channel().writeAndFlush(tws);
        }


    }

    /**
     * 处理 http请求类型消息
     * @param ctx
     * @param req
     */
    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req) {
        //要求 Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            // 自定义返回
            BaseResponse rest = BaseResponse.newInstance();
            rest.success("this is httpServer");

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(JSON.toJSONString(rest).getBytes()));

            HttpHeaders heads = response.headers();
            heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
            heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

            sendHttpResponse(ctx,response);
            return;
        }

        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory(
                        "ws://localhost:8081/websocket",
                        null,
                        false);

        handshaker = wsFactory.newHandshaker(req);

        if (null == handshaker) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private static final AsciiString contentType = HttpHeaderValues.APPLICATION_JSON;

    /**
     * 返回 response
     * @param ctx
     * @param res
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, DefaultFullHttpResponse res) {
        ctx.write(res);
        ctx.channel().flush().disconnect();

    }
}
