package com.example.application;

import com.example.domain.ChatMessage;
import com.example.domain.TouristSpot;
import com.example.infrastructure.ChatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatMapper chatMapper;
    private final TouristSpotService touristSpotService;

    public ChatService(ChatMapper chatMapper, TouristSpotService touristSpotService) {
        this.chatMapper = chatMapper;
        this.touristSpotService = touristSpotService;
    }

    public List<ChatMessage> getChatMessages(int offset, int limit) {
        return chatMapper.findChatMessages(offset, limit);
    }

    public int getTotalMessages() {
        return chatMapper.countChatMessages();
    }

    public List<ChatMessage> getLatestMessages() {
        return chatMapper.findLatestMessages();
    }

    public void saveMessage(ChatMessage message) {
        chatMapper.insertMessage(message);
    }

    public List<TouristSpot> getSeoulTouristSpots() {
        return touristSpotService.getSeoulTouristSpots();
    }
}
