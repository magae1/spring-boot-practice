package org.conan.bootpractice.controller;

import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.conan.bootpractice.domain.Todo;
import org.conan.bootpractice.domain.TodoDTO;
import org.conan.bootpractice.domain.PageResultDTO;
import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.service.TodoService;


@Log4j2
@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno) {
        return todoService.read(tno);
    }

    @GetMapping("/add")
    public void basic() {
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public PageResultDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        return todoService.list(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> write(@RequestBody TodoDTO todoDTO) {
        log.info(todoDTO);
        Long tno = todoService.write(todoDTO);
        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable(name = "tno") Long tno, @RequestBody TodoDTO todoDTO) {
        log.info("modify...{}", todoDTO);
        todoDTO.setTno(tno);
        todoService.modify(todoDTO);
        return Map.of("result", "success");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> delete(@PathVariable(name = "tno") Long tno) {
        log.info("delete...{}", tno);
        todoService.delete(tno);
        return Map.of("result", "success");
    }

    @PostMapping("/modify")
    public String update(Todo todo, RedirectAttributes rattr) {
        log.info(todo);
        boolean success = todoService.update(todo);
        rattr.addFlashAttribute("success", success);
        return "redirect:/todo/list";
    }

    @PostMapping("/add")
    public String add(Todo todo, RedirectAttributes rattr) {
        log.info(todo);
        boolean success = todoService.create(todo);
        rattr.addFlashAttribute("success", success);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam Long tno, Model model) {
        Optional<Todo> res = todoService.getOne(tno);
        res.ifPresent(todo -> model.addAttribute("todo", todo));
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("tno") Long tno) {
        todoService.delete(tno);
        return "redirect:/todo/list";
    }
}
