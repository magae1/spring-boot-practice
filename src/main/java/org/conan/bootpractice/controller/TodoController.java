package org.conan.bootpractice.controller;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.conan.bootpractice.domain.Todo;
import org.conan.bootpractice.service.TodoService;


@Log4j2
@Controller
@RequestMapping("todo")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    @GetMapping("/add")
    public void basic() {
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("todoList", todoService.getTodoList());
        return "todo/list";
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
