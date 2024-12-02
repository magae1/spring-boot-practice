package org.conan.bootpractice;

import lombok.extern.log4j.Log4j2;
import org.conan.bootpractice.domain.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.conan.bootpractice.service.BoardService;


@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testWrite() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test title")
                .content("test content")
                .writer("test writer")
                .build();
        boardService.write(boardDTO);
        log.info(boardDTO);
    }

}
