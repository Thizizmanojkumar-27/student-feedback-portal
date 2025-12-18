package com.example.auth.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String rollNo;
    private String department;
    private String semester;

    private String subject;
    private String facultyName;

    private String knowledge;
    private String communication;
    private String clarity;
    private String interaction;

    @Column(length = 500)
    private String likes;

    @Column(length = 500)
    private String suggestions;
}

