package org.example.llmquestionboard.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.llmquestionboard.model.domain.Questions;

@Mapper
public interface QuestionsMapper {

    @Insert("INSERT INTO questions (question_id, user_id, title, content) VALUES (#{questionId}, #{userId}, #{title}, #{content})")
    void save(Questions questions);
}
