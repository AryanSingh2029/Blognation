package com.example.blogapi.repository;

import com.example.blogapi.entity.Like;
import com.example.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPost(Post post);
    boolean existsByPostAndUsername(Post post, String username);
    void deleteByPostAndUsername(Post post, String username);

}
