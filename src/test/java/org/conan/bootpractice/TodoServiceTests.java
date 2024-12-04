package org.conan.bootpractice;

import java.time.LocalDate;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.conan.bootpractice.domain.TodoDTO;
import org.conan.bootpractice.service.TodoService;
import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.domain.PageResultDTO;


@SpringBootTest
@Log4j2
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

    @Test
    public void testWrite() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            TodoDTO todoDTO = TodoDTO.builder()
                    .title("service test")
                    .writer("tester")
                    .complete(false)
                    .dueDate(LocalDate.of(2024, 5, 4))
                    .build();
            Long tno = todoService.write(todoDTO);
        });
//        log.info("tno...{}", tno);
    }

    @Test
    public void testRead() {
        Long tno = 1152L;
        TodoDTO todoDTO = todoService.read(tno);
        log.info("read...{}", todoDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        PageResultDTO<TodoDTO> result = todoService.list(pageRequestDTO);
        log.info(result);
    }
}
