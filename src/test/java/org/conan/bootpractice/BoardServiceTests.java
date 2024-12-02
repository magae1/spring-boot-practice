package org.conan.bootpractice;

import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.domain.BoardDTO;
import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.domain.PageResultDTO;
import org.conan.bootpractice.service.BoardService;



@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testWrite() {
        IntStream.range(1, 100).forEach(i -> {
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("test title" + i)
                    .content("test content" + i)
                    .build();
            boardService.write(boardDTO);
        });
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDto = PageRequestDTO.builder()
                .page(1)
                .size(5)
                .build();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDto);
        result.getDtoList().forEach(log::info);
    }

    @Test
    public void testPagedList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(5)
                .build();
        PageResultDTO<BoardDTO, Object[]> resultDTO = boardService.getList(pageRequestDTO);
        log.info("PREV : {}", resultDTO.isPrev());
        log.info("NEXT : {}", resultDTO.isNext());
        log.info("TOTAL PAGE : {}", resultDTO.getTotalPage());
        resultDTO.getDtoList().forEach(log::info);
        log.info(resultDTO.getPageNums());
    }
}
