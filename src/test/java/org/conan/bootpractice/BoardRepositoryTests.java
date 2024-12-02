package org.conan.bootpractice;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.conan.bootpractice.repository.BoardRepository;


@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInitBoardRepository() {
        log.info(boardRepository.getClass().getName());
    }

}
