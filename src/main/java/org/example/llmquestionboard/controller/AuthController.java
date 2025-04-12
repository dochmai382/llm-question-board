package org.example.llmquestionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String signup() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String nickname,
                         @RequestParam String password) {
        authService.signup(username, nickname, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        return "redirect;/";
    }
}
