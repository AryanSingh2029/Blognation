package com.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // ✅ Renamed to avoid PostgreSQL keyword clash
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;
}
