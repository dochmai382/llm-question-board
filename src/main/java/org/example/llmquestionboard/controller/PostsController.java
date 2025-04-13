package org.example.llmquestionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.model.security.CustomUserDetails;
import org.example.llmquestionboard.service.QuestionsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class PostsController {

    private final QuestionsService questionsService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("questionList", questionsService.getQuestions());
        return "question/questionList";
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

    @GetMapping("/{questionId}")
    public String detail(@PathVariable String questionId, Model model) {
        model.addAttribute("question", questionsService.getQuestion(questionId));
        return "question/questionDetail";
    }

}
