package cn.edu.hfut.backend.socket.handler.impl;

import cn.edu.hfut.backend.constant.message.MessageType;
import cn.edu.hfut.backend.constant.socket.SocketMessageType;
import cn.edu.hfut.backend.dto.socket.ChatMessage;
import cn.edu.hfut.backend.dto.socket.SocketMessage;
import cn.edu.hfut.backend.entity.GroupUserList;
import cn.edu.hfut.backend.entity.Message;
import cn.edu.hfut.backend.service.GroupService;
import cn.edu.hfut.backend.service.MessageService;
import cn.edu.hfut.backend.socket.ChatSocket;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;
import cn.edu.hfut.backend.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class GroupMessageHandler implements SocketMessageHandler {
    private static MessageService messageService;
    private static GroupService groupService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        GroupMessageHandler.messageService = messageService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        GroupMessageHandler.groupService = groupService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        // 解析 chatMessage
        ChatMessage chatMessage = JsonUtil.parse(data, ChatMessage.class);
        Integer groupId = chatMessage.getToId();
        String content = chatMessage.getContent();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // 保存消息记录
        Message message = messageService.addMessage(
                chatSocket.getUserId(),
                null,
                groupId,
                MessageType.GROUP_MESSAGE,
                content,
                timestamp,
                // 群消息的是否已读不在此维护
                null
        );
        // 构造响应的 socket 消息
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setData(JsonUtil.stringify(message));
        socketMessage.setSocketMessageType(SocketMessageType.GROUP_MESSAGE);
        String socketMessageString = JsonUtil.stringify(socketMessage);
        List<GroupUserList> users = groupService.getGroupUserList(groupId);
        // 将消息发送给群友
        users.forEach(user -> {
            Integer userId = user.getID();
            ChatSocket userSocket = chatSocket.getChatSocketByUserId(userId);
            userSocket.getSession().getAsyncRemote().sendText(socketMessageString);
        });
    }
}
