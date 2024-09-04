package com.example.api;

import com.example.application.ReceivedMessageService;
import com.example.domain.ReceivedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
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
}