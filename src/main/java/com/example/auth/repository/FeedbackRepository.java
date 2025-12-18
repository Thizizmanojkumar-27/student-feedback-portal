package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.auth.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

