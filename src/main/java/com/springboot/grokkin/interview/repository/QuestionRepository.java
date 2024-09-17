package com.springboot.grokkin.interview.repository;

import com.springboot.grokkin.interview.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandom();
}
