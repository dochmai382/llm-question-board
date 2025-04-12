package org.example.llmquestionboard.service;

import lombok.RequiredArgsConstructor;
import org.example.llmquestionboard.model.domain.Users;
import org.example.llmquestionboard.model.dto.SignupDTO;
import org.example.llmquestionboard.model.mapper.UsersMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

//    public void signup(String username, String nickname, String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//        Users joinUser = Users.joinUsers(username, encodedPassword, nickname);
//        usersMapper.insertUser(joinUser);
//    }

    public void signup(SignupDTO signupDTO) {
        String encodedPassword = passwordEncoder.encode(signupDTO.password());
        Users joinUser = Users.joinUsers(signupDTO.username(), encodedPassword, signupDTO.nickname());
        usersMapper.insertUser(joinUser);
    }

    public boolean isDuplicateUsername(String username) {
        return usersMapper.countByUsername(username) > 0;

    }

    public boolean isNicknameTaken(String nickname) {
        return usersMapper.countByNickname(nickname) > 0;
    }
}
