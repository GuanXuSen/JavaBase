package com.gx.sbd.netty.websocket.entity;

import lombok.Data;

/**
 * @ClassName : MessageEntity
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/16 9:55
 * @Version : 1.0
 */
@Data
public class MessageEntity {

   private String sendUserId;

   private String sendMessage;

   private String toSendUserId;
}
