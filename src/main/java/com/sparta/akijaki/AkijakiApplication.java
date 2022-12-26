package com.sparta.akijaki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AkijakiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkijakiApplication.class, args);
    }
}
