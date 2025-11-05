package com.onboarding; // Changed package

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

// Classes in the same package are automatically available
// (SequenceData, SequenceDataRepository)

@Service
public class StreamingService {

    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final AtomicLong counter = new AtomicLong(0);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SequenceDataRepository repository;

    public void addSession(WebSocketSession session) {
        sessions.add(session);
        System.out.println("New session added: " + session.getId());
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
        System.out.println("Session removed: " + session.getId());
    }

    @Scheduled(fixedRate = 2000)
    public void sendSequenceData() {
        long number = counter.incrementAndGet();
        String distinctString = UUID.randomUUID().toString();
        SequenceData data = new SequenceData(number, distinctString);

        repository.save(data);

        try {
            String jsonData = objectMapper.writeValueAsString(data);
            TextMessage message = new TextMessage(jsonData);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        System.err.println("Error sending message to session " + session.getId() + ": " + e.getMessage());
                        sessions.remove(session);
                    }
                } else {
                    sessions.remove(session);
                }
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing data to JSON: " + e.getMessage());
        }
    }
}