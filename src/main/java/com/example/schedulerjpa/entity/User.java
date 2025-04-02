package com.example.schedulerjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String userName;

    @Column(nullable = false)
    String email;

    public User() {
    }

    public User (String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
