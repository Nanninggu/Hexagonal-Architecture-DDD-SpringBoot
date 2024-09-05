package com.example.infrastructure;

import com.example.domain.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {
    @Select("SELECT * FROM chat_message ORDER BY id DESC LIMIT 10")
    List<ChatMessage> findLatestMessages();

    @Insert("INSERT INTO chat_message (message) VALUES (#{message})")
    void insertMessage(ChatMessage message);

    @Select("SELECT * FROM chat_messages ORDER BY id DESC LIMIT #{limit} OFFSET #{offset}")
    List<ChatMessage> findChatMessages(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM chat_messages")
    int countChatMessages();
}
