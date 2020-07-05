package cn.edu.hfut.backend.socket.handler.impl;

import cn.edu.hfut.backend.constant.message.MessageState;
import cn.edu.hfut.backend.constant.message.MessageType;
import cn.edu.hfut.backend.constant.socket.SocketMessageType;
import cn.edu.hfut.backend.dto.socket.ChatMessage;
import cn.edu.hfut.backend.dto.socket.SocketMessage;
import cn.edu.hfut.backend.entity.Message;
import cn.edu.hfut.backend.service.MessageService;
import cn.edu.hfut.backend.socket.ChatSocket;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;
import cn.edu.hfut.backend.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class PrivateMessageHandler implements SocketMessageHandler {
    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        PrivateMessageHandler.messageService = messageService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        // 解析 chatMessage
        ChatMessage chatMessage = JsonUtil.parse(data, ChatMessage.class);
        Integer friendId = chatMessage.getToId();
        String content = chatMessage.getContent();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // 保存消息记录
        Message message = messageService.addMessage(
                chatSocket.getUserId(),
                friendId,
                null,
                MessageType.PRIVATE_MESSAGE,
                content,
                timestamp,
                MessageState.NEW_MESSAGE
        );
        // 构造响应的 socket 消息
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setData(JsonUtil.stringify(message));
        socketMessage.setSocketMessageType(SocketMessageType.PRIVATE_MESSAGE);
        String socketMessageString = JsonUtil.stringify(socketMessage);
        // 将消息发送给好友
        ChatSocket friendSocket = chatSocket.getChatSocketByUserId(friendId);
        if (friendSocket != null) {
            friendSocket.getSession().getAsyncRemote().sendText(socketMessageString);
        }
        // 回传消息对象
        chatSocket.getSession().getAsyncRemote().sendText(socketMessageString);
    }
}
