package cn.edu.hfut.backend.socket.handler.impl;

import cn.edu.hfut.backend.dto.socket.MarkGroupMessageRead;
import cn.edu.hfut.backend.service.GroupService;
import cn.edu.hfut.backend.socket.ChatSocket;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;
import cn.edu.hfut.backend.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarkGroupMessageReadHandler implements SocketMessageHandler {
    private static GroupService groupService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        MarkGroupMessageReadHandler.groupService = groupService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        MarkGroupMessageRead markGroupMessageRead = JsonUtil.parse(data, MarkGroupMessageRead.class);
        Integer groupId = markGroupMessageRead.getGroupId();
        Integer messageId = markGroupMessageRead.getMessageId();
        groupService.updateLastReadMessageId(chatSocket.getUserId(), groupId, messageId);
    }
}
