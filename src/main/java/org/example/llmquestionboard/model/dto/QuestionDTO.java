package org.example.llmquestionboard.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionDTO {
    private String questionId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String nickname;
}
