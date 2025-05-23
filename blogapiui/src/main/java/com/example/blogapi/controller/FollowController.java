package com.example.blogapi.controller;

import com.example.blogapi.entity.Follow;
import com.example.blogapi.entity.User;
import com.example.blogapi.payload.UserDto;
import com.example.blogapi.repository.FollowRepository;
import com.example.blogapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowRepository followRepo;
    private final UserRepository userRepo;

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        User follower = userRepo.findById(followerId).orElse(null);
        User following = userRepo.findById(followingId).orElse(null);

        if (follower == null || following == null) {
            return ResponseEntity.badRequest().body("Invalid user ID(s)");
        }

        // Prevent duplicate follow
        if (followRepo.findByFollowerAndFollowing(follower, following).isPresent()) {
            return ResponseEntity.badRequest().body("Already following");
        }

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepo.save(follow);
        return ResponseEntity.ok("Followed successfully");
    }

    @DeleteMapping("/{followerId}/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        User follower = userRepo.findById(followerId).orElse(null);
        User following = userRepo.findById(followingId).orElse(null);

        if (follower == null || following == null) {
            return ResponseEntity.badRequest().body("Invalid user ID(s)");
        }

        Follow follow = followRepo.findByFollowerAndFollowing(follower, following).orElse(null);
        if (follow != null) {
            followRepo.delete(follow);
            return ResponseEntity.ok("Unfollowed successfully");
        }

        return ResponseEntity.badRequest().body("You are not following this user");
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getFollowingUsers(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return ResponseEntity.badRequest().build();

        List<Follow> follows = followRepo.findByFollower(user);
        List<User> following = follows.stream().map(Follow::getFollowing).toList();

        return ResponseEntity.ok(following);
    }
 @GetMapping("/{userId}/followers")
public ResponseEntity<List<UserDto>> getFollowers(@PathVariable Long userId) {
    User user = userRepo.findById(userId).orElse(null);
    if (user == null) return ResponseEntity.badRequest().build();

    List<Follow> followers = followRepo.findByFollowing(user);
    List<UserDto> followerUsers = followers.stream()
            .map(f -> new UserDto(f.getFollower().getId(), f.getFollower().getUsername(), f.getFollower().getEmail()))
            .toList();

    return ResponseEntity.ok(followerUsers);
}
}
