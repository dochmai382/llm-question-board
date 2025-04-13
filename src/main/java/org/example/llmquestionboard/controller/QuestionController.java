package org.example.llmquestionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.model.dto.QuestionDTO;
import org.example.llmquestionboard.model.security.CustomUserDetails;
import org.example.llmquestionboard.service.QuestionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionsService;

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
            String questionId = questionsService.insertQuestion(userId, title, content);
            return "redirect:/question/" + questionId;
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

    @PostMapping("/delete/{questionId}")
    public String delete(@PathVariable String questionId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        QuestionDTO question = questionsService.getQuestion(questionId);
        if (question == null || !question.getUserId().equals(userDetails.getUserId())) {
            return "redirect:/question";
        }
        questionsService.deleteQuestion(questionId);
        return "redirect:/question";
    }

    @GetMapping("/edit/{questionId}")
    public String edit(@PathVariable String questionId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        QuestionDTO question = questionsService.getQuestion(questionId);
        if (!question.getUserId().equals(userDetails.getUserId())) {
            return "redirect:/question";
        }

        model.addAttribute("question", question);
        return "question/questionEdit";
    }

    @PostMapping("/edit/{questionId}")
    public String edit(@PathVariable String questionId,
                       @RequestParam String title, @RequestParam String content) {
        questionsService.updateQuestion(questionId, title, content);
        return "redirect:/question/" + questionId;
    }
}
