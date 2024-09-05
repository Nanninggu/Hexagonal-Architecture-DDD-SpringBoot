package com.example.application;

import com.example.domain.ReceivedMessage;
import com.example.infrastructure.tcp_ip.ReceivedMessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivedMessageService {
    private final ReceivedMessageMapper receivedMessageMapper;

    public ReceivedMessageService(ReceivedMessageMapper receivedMessageMapper) {
        this.receivedMessageMapper = receivedMessageMapper;
    }

    public List<ReceivedMessage> getMessages() {
        return receivedMessageMapper.findAll();
    }

    public List<ReceivedMessage> getLatestMessages() {
        return receivedMessageMapper.findLatestMessages();
    }
}
