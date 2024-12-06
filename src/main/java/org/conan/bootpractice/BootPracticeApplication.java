package org.conan.bootpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing
@SpringBootApplication
public class BootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPracticeApplication.class, args);
    }

}
