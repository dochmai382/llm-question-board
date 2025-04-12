package org.example.llmquestionboard.controller;

import org.example.llmquestionboard.model.security.CustomUserDetails;
import org.example.llmquestionboard.service.QuestionsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/questions")
public class PostsController {

    private final QuestionsService questionsService;

    public PostsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("/new")
    public String newPost() {
        return "question/questionForm";
    }

    @PostMapping("/new")
    public String newPost(@RequestParam String title,
                          @RequestParam String content,
                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                          Model model) {

        String userId = customUserDetails.getUserId();
        try {
            questionsService.insertQuestion(userId, title, content);
//        return "redirect:/questions";
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "question/questionForm"; //
        }
    }

}
