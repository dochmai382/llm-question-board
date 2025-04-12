package org.example.llmquestionboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.model.dto.SignupDTO;
import org.example.llmquestionboard.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupDTO", new SignupDTO("", "", "", ""));
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupDTO signupDTO, BindingResult bindingResult) {
//        authService.signup(username, nickname, password);

        if (authService.isDuplicateUsername(signupDTO.username())) {
          bindingResult.rejectValue("username", "username.exists", "이미 사용 중인 아이디입니다.");
          return "auth/signup";
        }

        if (authService.isNicknameTaken(signupDTO.nickname())) {
            bindingResult.rejectValue("nickname", "nickname.exists", "이미 사용 중인 닉네임입니다.");
            return "auth/signup";
        }

        if(!signupDTO.password().equals(signupDTO.confirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "비밀번호가 일치하지 않습니다.");
            return "auth/signup";
        }

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        authService.signup(signupDTO);
        return "redirect:/login";
    }

    // 유저네임 중복 AJAX 체크
    @ResponseBody
    @GetMapping("/check-username")
    public boolean checkUsername(@RequestParam String username) {
        return authService.isDuplicateUsername(username);
    }

    // 닉네임 중복 AJAX 체크
    @ResponseBody
    @GetMapping("/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        return authService.isNicknameTaken(nickname);
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디나 비밀번호가 잘못되었습니다.");
        }
        return "auth/login";
    }

}
