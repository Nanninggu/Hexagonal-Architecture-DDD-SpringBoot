package com.example.infrastructure;

import com.example.domain.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {

    List<ChatMessage> findLatestMessages();

    void insertMessage(ChatMessage message);

    List<ChatMessage> findChatMessages(@Param("offset") int offset, @Param("limit") int limit);

    int countChatMessages();
}
