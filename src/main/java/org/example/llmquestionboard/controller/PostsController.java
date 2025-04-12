package org.example.llmquestionboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class PostsController {

    @GetMapping("/new")
    public String newPost() {
        return "question/questionForm";
    }


}
