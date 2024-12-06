package org.conan.bootpractice.repository;

import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.conan.bootpractice.domain.entity.Subscriber;
import org.conan.bootpractice.domain.enums.SubscriberRole;


@SpringBootTest
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriberRepositoryTest {
    private final SubscriberRepository subscriberRepository;
    private final PasswordEncoder passwordEncoder;

    @Test
    public void testInsertSubscriber() {
        IntStream.range(0, 10).forEach(i -> {
            Subscriber subscriber = Subscriber.builder()
                    .email("user" + i + "@example.com")
                    .pwd(passwordEncoder.encode("1111"))
                    .nickname("USER" + i)
                    .social(false).build();
            subscriber.addRole(SubscriberRole.USER);
            if (i >= 5) {
                subscriber.addRole(SubscriberRole.MANAGER);
            }
            if (i >= 8) {
                subscriber.addRole(SubscriberRole.ADMIN);
            }
            subscriberRepository.save(subscriber);
        });
    }

    @Test
    public void testRead() {
        String email = "user9@example.com";
        Object result = subscriberRepository.getWithRoles(email);
        log.info(result);
//        Object[] arr = (Object[]) result;
//        for (Object o : arr) {
//            log.info(o);
//        }
    }
}
