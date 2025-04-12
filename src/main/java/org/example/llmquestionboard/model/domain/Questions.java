package org.example.llmquestionboard.model.domain;

import java.util.UUID;

public record Questions(
        String questionId,
        Long userId,
        String title,
        String content,
        String createdAt,
        String updatedAt,
        Boolean isDeleted
) {
    public static Questions createQuestions(Long userId, String title, String content) {
        return new Questions(UUID.randomUUID().toString(), userId, title, content, null, null, false);
    }
}
