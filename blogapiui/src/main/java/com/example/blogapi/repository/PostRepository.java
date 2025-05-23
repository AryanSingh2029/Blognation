package com.example.blogapi.repository;
import java.util.List;

import com.example.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // You can define custom query methods here if needed later
   List<Post> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

} 
