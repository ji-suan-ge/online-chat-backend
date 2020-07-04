package cn.edu.hfut.backend.socket.handler.impl;

import cn.edu.hfut.backend.service.MessageService;
import cn.edu.hfut.backend.socket.ChatSocket;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;
import cn.edu.hfut.backend.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarkReadMessageHandler implements SocketMessageHandler {
    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        MarkReadMessageHandler.messageService = messageService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        Integer friendId = JsonUtil.parse(data, Integer.class);
        messageService.readAllPrivateMessage(chatSocket.getUserId(), friendId);
    }
}
