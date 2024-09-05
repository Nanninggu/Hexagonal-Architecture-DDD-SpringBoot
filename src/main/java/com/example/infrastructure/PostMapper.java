package com.example.infrastructure;

import com.example.domain.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> findAll();

    Post findById(Long id);

    void insert(Post post);

    List<Post> findPostsByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int countPosts();
}