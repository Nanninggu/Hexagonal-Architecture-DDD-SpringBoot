package com.example.infrastructure.tcp_ip;

public interface ReceivedMessageMapper {

    Integer getLastId();

    void insertMessage(int id, String message);
}