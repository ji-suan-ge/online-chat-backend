package cn.edu.hfut.backend.socket.handler.impl;

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
        Integer groupId = JsonUtil.parse(data, Integer.class);
        Integer messageId = groupService.getLastMessageId(groupId, chatSocket.getUserId());
        groupService.updateLastReadMessageId(groupId, chatSocket.getUserId(), messageId);
    }
}
