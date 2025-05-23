package com.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who follows
    @ManyToOne
    private User follower;

    // The user being followed (the author)
    @ManyToOne
    private User following;
}
