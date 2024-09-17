package com.springboot.grokkin.interview.repository;

import com.springboot.grokkin.interview.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
