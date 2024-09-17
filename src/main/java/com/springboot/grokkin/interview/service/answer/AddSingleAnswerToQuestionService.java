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
public class AddSingleAnswerToQuestionService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public Long addAnswerToQuestion(final Long questionId, final String answerText) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: %d".formatted(questionId)));

        Answer savedAnswer = answerRepository.save(new Answer(answerText, question));
        question.addAnswer(savedAnswer);

        questionRepository.save(question);

        return savedAnswer.getId();
    }
}
