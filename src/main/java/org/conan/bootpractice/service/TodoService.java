package org.conan.bootpractice.service;

import java.util.List;
import java.util.Optional;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.conan.bootpractice.domain.Todo;
import org.conan.bootpractice.repository.TodoRepository;


@Log4j2
@Service
public class TodoService {
    @Setter(onMethod_ = {@Autowired})
    private TodoRepository todoRepository;

    public List<Todo> getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        log.info("getTodoList: {} items", todoList.size());
        return todoList;
    }

    public void toggleComplete(Long tno, boolean complete) {
        Optional<Todo> res = todoRepository.findById(tno);
        if (res.isPresent()) {
            Todo todo = res.get();
            Todo newTodo = Todo.builder()
                    .tno(todo.getTno())
                    .title(todo.getTitle())
                    .writer(todo.getWriter())
                    .complete(complete)
                    .dueDate(todo.getDueDate())
                    .build();
            todoRepository.save(newTodo);
        }
    }

    public Optional<Todo> getOne(Long tno) {
        log.info("getOne: {}", tno);
        return todoRepository.findById(tno);
    }

    public boolean create(Todo newTodo) {
        try {
            todoRepository.save(newTodo);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        log.info("create {}", newTodo);
        return false;
    }

    public boolean update(Todo updatedTodo) {
        try {
            todoRepository.save(updatedTodo);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        log.info("update {}", updatedTodo);
        return true;
    }

    public boolean delete(Long tno) {
        try {
            todoRepository.deleteById(tno);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        log.info("delete {}", tno);
        return true;
    }
}
