package com.example.infrastructure;

import com.example.domain.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("SELECT * FROM posts")
    List<Post> findAll();

    @Select("SELECT * FROM posts WHERE id = #{id}")
    Post findById(Long id);

    @Insert("INSERT INTO posts (title, author, date, views, content) VALUES (#{title}, #{author}, #{date}, #{views}, #{content})")
    void insert(Post post);

    @Select("SELECT * FROM posts ORDER BY id DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<Post> findPostsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM posts")
    int countPosts();
}