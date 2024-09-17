package com.springboot.grokkin.interview.domain.answer;

import com.springboot.grokkin.interview.domain.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Answer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String answerText;
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false, unique = true)
    private Question question;

    public Answer(Long id, String answerText, Question question) {
        this.id = Objects.requireNonNull(id, "Question id must not be null");
        this.answerText = Objects.requireNonNull(answerText, "Answer text must not be null");
        this.question = Objects.requireNonNull(question, "Question must not be null");
    }

    public Answer(String answerText, Question question) {
        this.answerText = Objects.requireNonNull(answerText, "Answer text must not be null");
        this.question = Objects.requireNonNull(question, "Question must not be null");
    }
}
