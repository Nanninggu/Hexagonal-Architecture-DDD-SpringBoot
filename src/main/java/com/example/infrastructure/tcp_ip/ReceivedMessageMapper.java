package com.example.infrastructure.tcp_ip;

import com.example.domain.ReceivedMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("SELECT * FROM received_messages LIMIT #{size} OFFSET #{offset}")
    List<ReceivedMessage> findReceivedMessages(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM received_messages")
    int countTotalMessages();
}
