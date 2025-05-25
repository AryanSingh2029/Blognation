package com.example.blogapi.service;

import com.example.blogapi.entity.Post;
import com.example.blogapi.entity.User;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return post != null ? mapToDto(post) : null;
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(postDto.getTitle());
            existingPost.setContent(postDto.getContent());
            existingPost.setAuthor(postDto.getAuthor());
            existingPost.setImageUrl(postDto.getImageUrl());
            Post updatedPost = postRepository.save(existingPost);
            return mapToDto(updatedPost);
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 🔹 Create a post for a specific user
    @Override
    public PostDto createPostForUser(Long userId, PostDto postDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = mapToEntity(postDto);
        post.setUser(user);
        post.setAuthor(user.getUsername());

        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    // 🔹 Get all posts by a specific user
    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Mapping methods
    private PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setImageUrl(post.getImageUrl());
        dto.setUserId(post.getUser() != null ? post.getUser().getId() : null); // include userId
        return dto;
    }

    private Post mapToEntity(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setImageUrl(dto.getImageUrl());
        return post;
    }
}
