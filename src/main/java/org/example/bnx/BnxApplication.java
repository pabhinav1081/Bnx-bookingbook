package org.example.bnx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BnxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BnxApplication.class, args);
    }

}
