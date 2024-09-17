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
public class AddListOfAnswersToQuestionService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void addAnswersToQuestion(final Long questionId, final String... answers) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: %d".formatted(questionId)));

        for (var answer : answers) {
            Answer savedAnswer = answerRepository.save(new Answer(answer, question));
            question.addAnswer(savedAnswer);
        }

        questionRepository.save(question);
    }
}
