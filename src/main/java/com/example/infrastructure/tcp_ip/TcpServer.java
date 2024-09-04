package com.example.infrastructure.tcp_ip;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TcpServer {
    private static final Logger logger = Logger.getLogger(TcpServer.class.getName());
    private int serverPort;
    private ServerSocket serverSocket;
    private SqlSessionFactory sqlSessionFactory;

    public TcpServer(SqlSessionFactory sqlSessionFactory) {
        this.serverPort = 5000; // Default port
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        logger.info("Server started on port " + serverPort);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            logger.info("Client connected: " + clientSocket.getInetAddress());
            new ClientHandler(clientSocket, sqlSessionFactory).start();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
        private SqlSessionFactory sqlSessionFactory;

        public ClientHandler(Socket socket, SqlSessionFactory sqlSessionFactory) {
            this.clientSocket = socket;
            this.sqlSessionFactory = sqlSessionFactory;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    logger.info("Received: " + message);
                    saveMessageToDatabase(message);
                    out.println("Echo: " + message);
                }
            } catch (IOException e) {
                logger.severe("Error handling client: " + e.getMessage());
            } finally {
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (clientSocket != null) clientSocket.close();
                    logger.info("Client disconnected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    logger.severe("Error closing resources: " + e.getMessage());
                }
            }
        }

        private void saveMessageToDatabase(String message) {
            try (SqlSession session = sqlSessionFactory.openSession()) {
                ReceivedMessageMapper mapper = session.getMapper(ReceivedMessageMapper.class);
                Integer lastId = mapper.getLastId();
                int newId = (lastId == null) ? 1 : lastId + 1;
                mapper.insertMessage(newId, message);
                session.commit();
            }
        }
    }

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        TcpServer server = new TcpServer(sqlSessionFactory);
        try {
            server.start();
        } catch (IOException e) {
            logger.severe("Server error: " + e.getMessage());
        }
    }
}