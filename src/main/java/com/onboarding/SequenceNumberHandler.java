package com.onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SequenceNumberHandler extends TextWebSocketHandler {

    @Autowired
    private StreamingService streamingService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        streamingService.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        streamingService.removeSession(session);
    }
}