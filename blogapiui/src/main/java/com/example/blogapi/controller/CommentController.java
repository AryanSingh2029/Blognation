package com.example.blogapi.controller;

import com.example.blogapi.entity.Comment;
import com.example.blogapi.entity.Post;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    @PostMapping("/{postId}")
    public ResponseEntity<?> addComment(@PathVariable Long postId,
                                        @RequestBody Comment comment) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setPost(post);  // Make sure Comment.java has `setPost(Post post)`
        comment.setCreatedAt(LocalDateTime.now());  // Make sure `createdAt` exists in Comment.java

        Comment saved = commentRepo.save(comment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{postId}")
public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
    Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    return ResponseEntity.ok(commentRepo.findByPost(post)); // ✅ Correct method
}

}
