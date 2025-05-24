package com.example.blogapi.controller;

import com.example.blogapi.entity.Post;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.LikeRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepo;
    @Autowired
private CommentRepository commentRepository;

@Autowired
private LikeRepository likeRepository;

@Autowired
private PostRepository postRepository; // In case it's not already there


    // Create a post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        PostDto post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Update post
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto updatedPost = postService.updatePost(id, postDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deletePost(@PathVariable Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Post not found"));

    // Delete all comments linked to the post
    commentRepository.deleteByPost(post);

    // Delete all likes linked to the post
    likeRepository.deleteByPost(post);

    // Now delete the post safely
    postRepository.delete(post);

    return ResponseEntity.ok().build();
}


    // Search posts by title or author
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String query) {
        List<Post> results = postRepo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
        return ResponseEntity.ok(results);
    }
}
