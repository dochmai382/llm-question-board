package org.example.llmquestionboard.model.mapper;

import org.apache.ibatis.annotations.*;
import org.example.llmquestionboard.model.domain.Questions;
import org.example.llmquestionboard.model.dto.QuestionDTO;

import java.util.List;

@Mapper
public interface QuestionsMapper {

    @Insert("INSERT INTO questions (question_id, user_id, title, content) VALUES (#{questionId}, #{userId}, #{title}, #{content})")
    void save(Questions questions);

    @Select("""
                SELECT q.question_id, q.title, q.content, q.created_at, u.nickname
                FROM questions q
                JOIN users u ON q.user_id = u.user_id
                ORDER BY q.created_at DESC
            """)
    @Results(
            @Result(property = "createdAt", column = "created_at")
    )
    List<QuestionDTO> findAllWithNickname();
}
