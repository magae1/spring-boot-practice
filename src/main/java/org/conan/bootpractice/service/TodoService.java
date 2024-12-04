package org.conan.bootpractice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.conan.bootpractice.domain.Todo;
import org.conan.bootpractice.domain.TodoDTO;
import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.domain.PageResultDTO;
import org.conan.bootpractice.repository.TodoRepository;
import org.springframework.web.bind.annotation.PutMapping;


@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    //    @Setter(onMethod_ = {@Autowired})
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public Long write(TodoDTO todoDTO) {
        log.info("writing...{}", todoDTO);
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        return todoRepository.save(todo).getTno();
    }

    public TodoDTO read(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return modelMapper.map(todo, TodoDTO.class);
    }

    public PageResultDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);
        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResultDTO.<TodoDTO>withAll().
                dtoList(dtoList).
                pageRequestDTO(pageRequestDTO).
                totalCount(totalCount).
                build();
    }

    public void modify(TodoDTO todoDTO) {
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        todoRepository.save(todo);
    }

    public void delete(Long tno) {
        todoRepository.deleteById(tno);
    }

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
}
