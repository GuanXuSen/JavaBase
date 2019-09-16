package com.gx.sbd.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @ClassName : ChannelSupervise
 * @Description :TOO 客户端信息
 * @Author : gx
 * @Date : 2019/9/12 15:40
 * @Version : 1.0
 */
public class ChannelSupervise {

    private static final Logger logger = LoggerFactory.getLogger(ChannelSupervise.class);


    private   static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 客户端
     */
    private  static ConcurrentMap<String, ChannelId> ChannelMap = new ConcurrentHashMap();

    /**
     * 客户端id
     */
    private  static ConcurrentMap<String, String> ChannelIdMap = new ConcurrentHashMap();


    public  static void addChannel(Channel channel){
        GlobalGroup.add(channel);
        ChannelMap.put(channel.id().asShortText(),channel.id());
    }

    public static void addChannelName(String channelName, String channelId){
        ChannelIdMap.put(channelName,channelId);
    }

    public static void sendByName(String channelName,TextWebSocketFrame tws){
        if(ChannelIdMap.containsKey(channelName)){
            findChannel(ChannelIdMap.get(channelName)).writeAndFlush(tws);
        }
    }

    public static void removeChannel(Channel channel){
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
        if(ChannelIdMap.containsValue(channel.id().asShortText())){
            for (Map.Entry<String, String> entry : ChannelIdMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (StringUtils.equals(v, channel.id().asShortText())) {
                    ChannelIdMap.remove(k);
                    break;
                }
            }
        }
    }

    public static  Channel findChannel(String id){
        return GlobalGroup.find(ChannelMap.get(id));
    }

    public static void send2All(TextWebSocketFrame tws){
        GlobalGroup.writeAndFlush(tws);
    }
}
