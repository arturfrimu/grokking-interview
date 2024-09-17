package com.springboot.grokkin.interview.service.answer;

import com.springboot.grokkin.interview.domain.answer.Answer;
import com.springboot.grokkin.interview.domain.question.Question;
import com.springboot.grokkin.interview.repository.AnswerRepository;
import com.springboot.grokkin.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarkAnswerAsCorrectForQuestion {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void markAsCorrect(final Long questionId, final Long answerId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: %d".formatted(questionId)));

        Answer correctAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: %d".formatted(questionId)));

        question.markAsCorrect(correctAnswer);

        questionRepository.save(question);
    }
}
