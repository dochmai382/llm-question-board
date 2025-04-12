package org.example.llmquestionboard.service;

import org.example.llmquestionboard.model.domain.Users;
import org.example.llmquestionboard.model.mapper.UsersMapper;
import org.example.llmquestionboard.model.security.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UsersMapper usersMapper;

    public CustomUserDetailService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        return new CustomUserDetails(
                user.username(), user.password(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_%s".formatted(user.role()))),
                user.nickname()
        );
    }
}
