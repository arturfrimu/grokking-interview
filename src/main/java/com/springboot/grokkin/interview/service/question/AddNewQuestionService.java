package com.springboot.grokkin.interview.service.question;

import com.springboot.grokkin.interview.domain.question.Question;
import com.springboot.grokkin.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewQuestionService {

    private final QuestionRepository questionRepository;

    public Long addNewQuestion(String questionText) {
        return questionRepository.save(new Question(questionText)).getId();
    }
}
