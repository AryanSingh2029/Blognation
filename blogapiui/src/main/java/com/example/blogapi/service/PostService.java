package com.example.blogapi.service;

import com.example.blogapi.payload.PostDto;
import java.util.List;

public interface PostService {

    // Existing methods
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);

    // 🔹 New methods for user-linked posts
    PostDto createPostForUser(Long userId, PostDto postDto);
    List<PostDto> getPostsByUserId(Long userId);
}
