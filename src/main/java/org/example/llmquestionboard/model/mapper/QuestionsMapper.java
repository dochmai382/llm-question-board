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
                WHERE q.is_deleted = FALSE
                ORDER BY q.created_at DESC
            """)
    @Results({
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "createdAt", column = "created_at")
    })
    List<QuestionDTO> findAllWithNickname();

    @Select("""
                SELECT q.user_id, q.question_id, q.title, q.content, q.created_at, q.updated_at, u.nickname
                FROM questions q
                JOIN users u ON q.user_id = u.user_id
                WHERE q.question_id = #{questionId}
            """)
    @Results({
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    QuestionDTO findByQuestionId(String questionId);

    @Update("UPDATE questions SET is_deleted = TRUE WHERE question_id = #{questionId}")
    void softDelete(String questionId);

    @Update("UPDATE questions SET title = #{title}, content = #{content} WHERE question_id = #{questionId}")
    void update(String questionId, String title, String content);
}
