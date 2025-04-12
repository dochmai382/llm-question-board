package org.example.llmquestionboard.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.llmquestionboard.model.domain.Users;

@Mapper
public interface UsersMapper {

    @Insert("INSERT INTO users (username, password, nickname) VALUES (#{username}, #{password}, #{nickname})")
    void insertUser(Users users);
}
