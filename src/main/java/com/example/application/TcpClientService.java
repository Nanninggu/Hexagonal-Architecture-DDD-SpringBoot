package com.example.application;

import com.example.infrastructure.tcp_ip.TcpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TcpClientService {
    private TcpClient tcpClient;

    public void startClient(String serverAddress) throws IOException {
        if (tcpClient == null) {
            tcpClient = new TcpClient(serverAddress);
            tcpClient.connect();
        }
    }

    public void stopClient() throws IOException {
        if (tcpClient != null) {
            tcpClient.close();
            tcpClient = null;
        }
    }
}
