package com.example.auth.controller;

import com.example.auth.model.Feedback;
import com.example.auth.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository repo;


    // ============================
    //      PUBLIC: SAVE FEEDBACK
    // ============================
    @PostMapping("/save")
    public void saveFeedback(@ModelAttribute Feedback feedback,
                             HttpServletResponse response) throws IOException {

        try {
            repo.save(feedback);
            response.sendRedirect("/profile.html?success=true");

        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().println("Error saving feedback:");
            ex.printStackTrace(response.getWriter());
            response.getWriter().flush();
        }
    }


    // ============================
    //   ADMIN ONLY: GET ALL DATA
    // ============================
    @GetMapping("/all")
    public List<Feedback> getAll() {
        return repo.findAll();   // returns JSON
    }
}
