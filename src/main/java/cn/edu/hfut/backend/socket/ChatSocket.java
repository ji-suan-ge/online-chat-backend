package cn.edu.hfut.backend.socket;

import cn.edu.hfut.backend.constant.socket.SocketMessageType;
import cn.edu.hfut.backend.dto.socket.SocketMessage;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;
import cn.edu.hfut.backend.socket.handler.impl.ApplyFriendHandler;
import cn.edu.hfut.backend.socket.handler.impl.GroupMessageHandler;
import cn.edu.hfut.backend.socket.handler.impl.MarkReadMessageHandler;
import cn.edu.hfut.backend.socket.handler.impl.PrivateMessageHandler;
import cn.edu.hfut.backend.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component
@ServerEndpoint("/chatSocket/{userId}")
public class ChatSocket {

    private static ConcurrentHashMap<Integer, ChatSocket> chatSockets = new ConcurrentHashMap<>();
    private Session session;
    private Integer userId;

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") Integer userId) {
        this.session = session;
        this.userId = userId;
        chatSockets.put(userId, this);
        log.debug("websocket connection established, userId: " + this.userId);
    }

    @OnClose
    public void onClose() {
        chatSockets.remove(this.userId);
        log.debug("websocket connection destroyed, userId: " + this.userId);
    }

    @OnMessage
    public void onMessage(String message) throws JsonProcessingException {
        log.debug("=======================================================================");
        log.debug("userId: " + this.userId);
        log.debug("message:" + message);
        // 解析 socket 消息
        SocketMessage socketMessage = JsonUtil.parse(message, SocketMessage.class);
        Integer messageType = socketMessage.getSocketMessageType();
        String data = socketMessage.getData();
        // 判断 socket 事件类型
        SocketMessageHandler socketMessageHandler = null;
        if (SocketMessageType.PRIVATE_MESSAGE.equals(messageType)) {
            socketMessageHandler = new PrivateMessageHandler();
        } else if (SocketMessageType.MARK_READ_MESSAGE.equals(messageType)) {
            socketMessageHandler = new MarkReadMessageHandler();
        } else if (SocketMessageType.FRIEND_APPLY.equals(messageType)) {
            socketMessageHandler = new ApplyFriendHandler();
        } else if (SocketMessageType.GROUP_MESSAGE.equals(messageType)) {
            socketMessageHandler = new GroupMessageHandler();
        } else {
            // 忽略不支持的事件类型
            return;
        }
        // 处理 socket 事件
        socketMessageHandler.handle(this, data);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Session getSession() {
        return this.session;
    }

    public ChatSocket getChatSocketByUserId(Integer userId) {
        return ChatSocket.chatSockets.get(userId);
    }
}
