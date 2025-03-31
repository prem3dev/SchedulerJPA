package com.example.schedulerjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String task;

    public Schedule() {
    }

    public Schedule(String authorName, String title, String task) {
        this.authorName = authorName;
        this.title = title;
        this.task = task;
    }
}
