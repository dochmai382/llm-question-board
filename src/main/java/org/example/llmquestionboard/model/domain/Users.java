package org.example.llmquestionboard.model.domain;

public record Users(
        Long userId, // 식별자
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
        return new Users(0L, username, password, nickname, "USER", true, 0, 0, false);
    }
}
