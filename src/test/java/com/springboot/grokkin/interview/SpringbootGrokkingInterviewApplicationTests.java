package com.springboot.grokkin.interview;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
class SpringbootGrokkingInterviewApplicationTests {

    private final ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }
}
