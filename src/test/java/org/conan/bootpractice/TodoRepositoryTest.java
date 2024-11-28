package org.conan.bootpractice;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.conan.bootpractice.domain.Todo;
import org.conan.bootpractice.repository.TodoRepository;


@SpringBootTest
@Log4j2
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testClass() {
        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("title..." + i)
                    .writer("writer..." + i)
                    .complete(false)
                    .dueDate(LocalDate.of(2024, 11, 28))
                    .build();
            todoRepository.save(todo);
        });
    }

    @Test
    public void testSelect() {
        Long tno = 10L;
        Optional<Todo> res = todoRepository.findById(tno);
        if (res.isPresent()) {
            log.info(res.get());
        } else {
            log.info("Can't find " + tno);
        }
    }

    @Test
    public void testUpdate() {
        Long tno = 11L;
        Todo todo = Todo.builder()
                .tno(tno)
                .title("updated title")
                .writer("updated writer")
                .complete(true)
                .dueDate(LocalDate.of(2024, 11, 28))
                .build();
        log.info(todoRepository.save(todo));
    }

    @Test
    public void testDelete() {
        Long tno = 10L;
        todoRepository.deleteById(tno);
        log.info("deleted!: " + tno);
    }

}
