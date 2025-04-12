package org.example.llmquestionboard.service;

import java.time.LocalDateTime;
import org.example.llmquestionboard.model.domain.Questions;
import org.example.llmquestionboard.model.dto.QuestionDTO;
import org.example.llmquestionboard.model.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsService {

    private final QuestionsMapper questionsMapper;

    public QuestionsService(QuestionsMapper questionsMapper) {
        this.questionsMapper = questionsMapper;
    }

    public void insertQuestion(String userId, String title, String content) {
        Questions questions = Questions.createQuestions(userId, title, content);
        try {
            questionsMapper.save(questions);
        } catch (Exception e) {
            throw new RuntimeException("등록 실패: 데이터베이스 오류");
        }
    }

    public List<QuestionDTO> getQuestions() {
        return questionsMapper.findAllWithNickname().stream().map(q -> {
            q.setCreatedAt(q.getCreatedAt().plusHours(9));
            return q;
        }).toList();
    }
}
