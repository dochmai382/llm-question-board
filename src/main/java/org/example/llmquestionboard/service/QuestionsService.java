package org.example.llmquestionboard.service;

import org.example.llmquestionboard.model.domain.Questions;
import org.example.llmquestionboard.model.dto.QuestionDTO;
import org.example.llmquestionboard.model.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    private final QuestionsMapper questionsMapper;

    public QuestionsService(QuestionsMapper questionsMapper) {
        this.questionsMapper = questionsMapper;
    }

    public String insertQuestion(String userId, String title, String content) {
        Questions questions = Questions.createQuestions(userId, title, content);
        try {
            questionsMapper.save(questions);
            return questions.questionId();
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

    public QuestionDTO getQuestion(String questionId) {
        QuestionDTO dto = questionsMapper.findByQuestionId(questionId);
        dto.setCreatedAt(dto.getCreatedAt().plusHours(9));
        dto.setUpdatedAt(dto.getUpdatedAt().plusHours(9));
        return dto;
    }

    public void deleteQuestion(String questionId) {
        questionsMapper.softDelete(questionId);
    }

    public void updateQuestion(String questionId, String title, String content) {
        questionsMapper.update(questionId, title, content);
    }
}
