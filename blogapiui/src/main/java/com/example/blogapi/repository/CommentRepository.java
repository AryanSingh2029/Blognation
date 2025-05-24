package com.example.blogapi.repository;

import com.example.blogapi.entity.Comment;
import com.example.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    void deleteByPost(Post post);

}
