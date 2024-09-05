package com.example.infrastructure.tcp_ip;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class TcpClient {
    private static final Logger logger = Logger.getLogger(TcpClient.class.getName());
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread messageThread;
    private boolean running;

    public TcpClient(String serverAddress) {
        this.serverAddress = serverAddress;
        this.serverPort = 5000; // Default port
    }

    public void connect() throws IOException {
        logger.info("Attempting to connect to server at " + serverAddress + ":" + serverPort);
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        logger.info("Connected to server");
        startSendingMessages();
    }

    private void startSendingMessages() {
        running = true;
        messageThread = new Thread(() -> {
            while (running) {
                try {
                    sendMessage("Hello, Server!");
                    Thread.sleep(2000); // Send message every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.severe("Message sending thread interrupted: " + e.getMessage());
                }
            }
        });
        messageThread.start();
    }

    public void sendMessage(String message) {
        logger.info("Sending message: " + message);
        out.println(message);
    }

    public void close() throws IOException {
        logger.info("Closing connection to server");
        running = false;
        if (messageThread != null) {
            messageThread.interrupt();
        }
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null) socket.close();
        logger.info("Connection to server closed");
    }
}
