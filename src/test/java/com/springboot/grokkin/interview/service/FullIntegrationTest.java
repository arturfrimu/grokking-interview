package com.springboot.grokkin.interview.service;

import com.springboot.grokkin.interview.domain.answer.Answer;
import com.springboot.grokkin.interview.service.answer.AddListOfAnswersToQuestionService;
import com.springboot.grokkin.interview.service.answer.AddSingleAnswerToQuestionService;
import com.springboot.grokkin.interview.service.answer.CheckAnswer;
import com.springboot.grokkin.interview.service.answer.MarkAnswerAsCorrectForQuestion;
import com.springboot.grokkin.interview.service.question.AddNewQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FullIntegrationTest {
    @Autowired
    private AddNewQuestionService addNewQuestionService;
    @Autowired
    private AddSingleAnswerToQuestionService addSingleAnswerToQuestionService;
    @Autowired
    private AddListOfAnswersToQuestionService addListOfAnswersToQuestionService;
    @Autowired
    private MarkAnswerAsCorrectForQuestion markAnswerAsCorrectForQuestion;
    @Autowired
    private CheckAnswer checkAnswer;

    @Test
    void testFull() {
        Long questionId = addNewQuestionService.addNewQuestion("Care este contractul dintre equals si hashCode?");
        Long answerId = addSingleAnswerToQuestionService.addAnswerToQuestion(questionId, "Consistență: Dacă doi obiecti sunt considerați egali prin metoda equals(), atunci apelând metoda hashCode() pentru fiecare dintre cele două obiecte trebuie să returneze același număr întreg.");
        addListOfAnswersToQuestionService.addAnswersToQuestion(
                questionId,
                "Consistență repetată: Apelând metoda hashCode() pe același obiect mai multe ori, fără a-i modifica starea, trebuie să returneze același număr întreg de fiecare dată.",
                "Inegalitate acceptată: Dacă doi obiecti sunt inegali conform metodei equals(), nu este necesar ca metoda hashCode() să returneze numere întregi diferite pentru acei obiecti, deși producerea de hash-uri diferite pentru obiecte inegale poate îmbunătăți performanța tabelelor hash."
        );

        markAnswerAsCorrectForQuestion.markAsCorrect(questionId, answerId);

        boolean isCorrect = checkAnswer.checkAnswer(questionId, answerId);
        assertThat(isCorrect).isTrue();
    }
}