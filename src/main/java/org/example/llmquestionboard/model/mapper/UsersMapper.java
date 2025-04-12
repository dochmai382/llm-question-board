package org.example.llmquestionboard.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.llmquestionboard.model.domain.Users;

@Mapper
public interface UsersMapper {

    @Insert("INSERT INTO users (username, password, nickname) VALUES (#{username}, #{password}, #{nickname})")
    void insertUser(Users users);

    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    int countByUsername(String username);

    @Select("SELECT COUNT(*) FROM users WHERE nickname = #{nickname}")
    int countByNickname(String nickname);

    @Select("SELECT * FROM users WHERE username = #{username}")
    Users findByUsername(String username);
}
