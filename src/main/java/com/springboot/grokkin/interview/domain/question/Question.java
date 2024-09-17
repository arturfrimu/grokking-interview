package com.springboot.grokkin.interview.domain.question;

import com.springboot.grokkin.interview.domain.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String questionText;
    @OneToOne
    @JoinColumn(name = "correct_answer_id")
    private Answer correctAnswer;
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private Set<Answer> answers = new LinkedHashSet<>();


    public Question(Long id, String questionText, Set<Answer> answers) {
        this.id = Objects.requireNonNull(id, "Question id must not be null");
        this.questionText = Objects.requireNonNull(questionText, "Question text must not be null");
        if (answers == null || answers.isEmpty()) {
            throw new NullPointerException("Answers must not be null or empty");
        }
        this.answers = answers;
    }

    public Question(String questionText) {
        this.questionText = Objects.requireNonNull(questionText, "Question text must not be null");
    }

    public void addAnswer(Answer savedAnswer) {
        Objects.requireNonNull(savedAnswer, "Answer must not be null");
        answers.add(savedAnswer);
    }

    public void markAsCorrect(Answer correctAnswer) {
        Objects.requireNonNull(correctAnswer, "Answer must not be null");
        this.correctAnswer = correctAnswer;
    }
}
