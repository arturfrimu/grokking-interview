package com.springboot.grokkin.interview.service.answer;

import com.springboot.grokkin.interview.domain.answer.Answer;
import com.springboot.grokkin.interview.domain.question.Question;
import com.springboot.grokkin.interview.repository.AnswerRepository;
import com.springboot.grokkin.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckAnswer {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public boolean checkAnswer(Long questionId, Long answerId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: %d".formatted(questionId)));

        Answer correctAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: %d".formatted(questionId)));

        return correctAnswer.equals(question.getCorrectAnswer());
    }
}
