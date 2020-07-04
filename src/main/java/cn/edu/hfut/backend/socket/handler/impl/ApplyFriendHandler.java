package cn.edu.hfut.backend.socket.handler.impl;

import cn.edu.hfut.backend.socket.ChatSocket;
import cn.edu.hfut.backend.socket.handler.SocketMessageHandler;

public class ApplyFriendHandler implements SocketMessageHandler {
    @Override
    public void handle(ChatSocket chatSocket, String data) {
//        PrivateMessage privateMessage = objectMapper.readValue(data, PrivateMessage.class);
//        Integer friendId = privateMessage.getFriendId();
//        String content = privateMessage.getContent();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        ChatSocket friendSocket = null;
//        for (ChatSocket chatSocket : webSocketSet) {
//            if (chatSocket.userId.equals(friendId)) {
//                friendSocket = chatSocket;
//            }
//        }
//        FriendRequest friendRequest = messageService.addFriendRequest(this.userId, friendId, content, timestamp);
//        SocketMessage socketMessage = new SocketMessage();
//        socketMessage.setSocketMessageType(SocketMessageType.FRIEND_APPLY);
//        socketMessage.setData(objectMapper.writeValueAsString(friendRequest));
//        String socketMessageString = objectMapper.writeValueAsString(socketMessage);
//        if (friendSocket != null) {
//            friendSocket.session.getAsyncRemote().sendText(socketMessageString);
//        }
//        session.getAsyncRemote().sendText(socketMessageString);
    }
}
