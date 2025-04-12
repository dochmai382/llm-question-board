package org.example.llmquestionboard.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final String nickname;  // 추가된 닉네임 필드

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String nickname) {
        super(username, password, authorities);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
