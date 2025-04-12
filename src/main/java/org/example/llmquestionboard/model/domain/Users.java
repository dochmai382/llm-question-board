package org.example.llmquestionboard.model.domain;

import java.util.UUID;

public record Users(
        String userId, // 식별자
        String username, // 로그인용
        String password,
        String nickname, // 별명
        String role,
        Boolean enabled,
        int points,
        int reportCount,
        boolean isBanned
) {
    public static Users joinUsers(String username, String password, String nickname) {
        return new Users(UUID.randomUUID().toString(), username, password, nickname, "USER", true, 0, 0, false);
    }
}
