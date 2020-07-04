package cn.edu.hfut.backend.socket.handler;

import cn.edu.hfut.backend.socket.ChatSocket;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SocketMessageHandler {
    void handle(ChatSocket chatSocket, String data) throws JsonProcessingException;
}
