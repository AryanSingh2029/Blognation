package com.example.blogapi.repository;

import java.util.List;

import com.example.blogapi.entity.Post;
import com.example.blogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 🔹 Search functionality
    List<Post> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

    // 🔹 NEW: Fetch all posts by a specific user
    List<Post> findByUser(User user);
}
