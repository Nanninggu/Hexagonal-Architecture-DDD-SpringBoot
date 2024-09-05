package com.example.api;

import com.example.application.ChatService;
import com.example.application.ReceivedMessageService;
import com.example.domain.ChatMessage;
import com.example.domain.ReceivedMessage;
import com.example.domain.TouristSpot;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {
    private final ChatService chatService;
    private final ReceivedMessageService receivedMessageService;

    public ChatController(ChatService chatService, ReceivedMessageService receivedMessageService) {
        this.chatService = chatService;
        this.receivedMessageService = receivedMessageService;
    }

    @GetMapping("/combined")
    public String getCombinedView(Model model) {
        List<ChatMessage> chatMessages = chatService.getLatestMessages();
        List<ReceivedMessage> receivedMessages = receivedMessageService.getLatestMessages();
        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("receivedMessages", receivedMessages);
        return "combined";
    }

    @PostMapping("/chat")
    public String postMessage(@RequestParam("message") String message, Model model) {
        if (message.startsWith("챗봇 : ")) {
            String query = message.substring(5).trim();
            if (query.equalsIgnoreCase("서울의 주요 관광지를 알려줘")) {
                List<TouristSpot> spots = chatService.getSeoulTouristSpots();
                model.addAttribute("spots", spots);
                return "tourist_spots";
            }
        } else {
            chatService.saveMessage(new ChatMessage(message));
        }
        return "redirect:/combined";
    }
}
