package org.example.llmquestionboard.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final String userId;
    private final String nickname;  // 추가된 닉네임 필드

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String nickname, String userId) {
        super(username, password, authorities);
        this.nickname = nickname;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserId() {
        return userId;
    }
}
