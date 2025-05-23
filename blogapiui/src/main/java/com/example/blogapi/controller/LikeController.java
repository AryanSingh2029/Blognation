package com.example.blogapi.controller;

import com.example.blogapi.entity.Like;
import com.example.blogapi.entity.Post;
import com.example.blogapi.repository.LikeRepository;
import com.example.blogapi.repository.PostRepository;
import jakarta.transaction.Transactional; // ✅ Required for DELETE
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeRepository likeRepo;
    private final PostRepository postRepo;

    // 🔹 Like a post
    @PostMapping("/{postId}")
    public ResponseEntity<?> likePost(@PathVariable Long postId, @RequestBody Like like) {
        Post post = postRepo.findById(postId).orElseThrow();
        if (likeRepo.existsByPostAndUsername(post, like.getUsername())) {
            return ResponseEntity.badRequest().body("User already liked this post");
        }
        like.setPost(post);
        return ResponseEntity.ok(likeRepo.save(like));
    }

    // 🔹 Get total like count
    @GetMapping("/{postId}")
    public ResponseEntity<?> getLikes(@PathVariable Long postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        return ResponseEntity.ok(likeRepo.findByPost(post).size());
    }

    // 🔹 Unlike a post
    @DeleteMapping("/{postId}")
    @Transactional // ✅ Ensure proper transaction for DELETE
    public ResponseEntity<?> unlikePost(@PathVariable Long postId, @RequestBody Like like) {
        Post post = postRepo.findById(postId).orElseThrow();
        likeRepo.deleteByPostAndUsername(post, like.getUsername());
        return ResponseEntity.ok("Post unliked");
    }

    // 🔹 Check if user has liked the post
    @GetMapping("/{postId}/liked/{username}")
    public ResponseEntity<Boolean> hasUserLiked(
        @PathVariable Long postId,
        @PathVariable String username
    ) {
        Post post = postRepo.findById(postId).orElseThrow();
        boolean liked = likeRepo.existsByPostAndUsername(post, username);
        return ResponseEntity.ok(liked);
    }
}
