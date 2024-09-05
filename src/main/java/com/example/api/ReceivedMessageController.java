package com.example.api;

import com.example.application.ReceivedMessageService;
import com.example.domain.ReceivedMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceivedMessageController {
    private final ReceivedMessageService receivedMessageService;

    public ReceivedMessageController(ReceivedMessageService receivedMessageService) {
        this.receivedMessageService = receivedMessageService;
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        List<ReceivedMessage> messages = receivedMessageService.getLatestMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @GetMapping("/api/entities/received-messages")
    public List<ReceivedMessage> getLatestMessages() {
        return receivedMessageService.getLatestMessages();
    }
}
