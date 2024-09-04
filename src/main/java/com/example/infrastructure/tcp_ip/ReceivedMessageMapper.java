package com.example.infrastructure.tcp_ip;

import com.example.domain.ReceivedMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReceivedMessageMapper {

    Integer getLastId();

    void insertMessage(int id, String message);

    @Select("SELECT * FROM received_message")
    List<ReceivedMessage> findAll();

    @Select("SELECT * FROM received_message ORDER BY id DESC LIMIT 10")
    List<ReceivedMessage> findLatestMessages();
}