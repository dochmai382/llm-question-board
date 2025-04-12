package org.example.llmquestionboard.service;

import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.model.domain.Users;
import org.example.llmquestionboard.model.mapper.UsersMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    public void signup(String username, String nickname, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Users joinUser = Users.joinUsers(username, encodedPassword, nickname);
        usersMapper.insertUser(joinUser);
    }
}
