package com.example.infrastructure.tcp_ip;

import com.example.domain.ReceivedMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReceivedMessageMapper {

    Integer getLastId();

    void insertMessage(@Param("id") int id, @Param("message") String message);

    List<ReceivedMessage> findAll();

    List<ReceivedMessage> findLatestMessages();

    List<ReceivedMessage> findReceivedMessages(@Param("offset") int offset, @Param("size") int size);

    int countTotalMessages();
}
