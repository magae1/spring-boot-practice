package org.conan.bootpractice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.conan.bootpractice.domain.SampleDTO;


@RequestMapping("/sample")
@Controller
@Log4j2
public class SampleController {
    @GetMapping("/ex1")
    public void ex1() {
        log.info("ex1");
    }

    @GetMapping({"/ex2", "/exLink"})
    public void ex2(Model model) {
        log.info("ex2");
        List<SampleDTO> list = IntStream.rangeClosed(1, 20)
                .asLongStream()
                .mapToObj(i -> SampleDTO.builder()
                        .sno(i)
                        .first("First name " + i)
                        .last("Last name " + i)
                        .regTime(LocalDateTime.now())
                        .build())
                .toList();
        model.addAttribute("list", list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes rattr) {
        log.info("exInline");
        SampleDTO sampleDto = SampleDTO.builder()
                .sno(100L)
                .first("FIRST__100")
                .last("LAST__100")
                .regTime(LocalDateTime.now())
                .build();
        rattr.addFlashAttribute("dto", sampleDto);
        rattr.addFlashAttribute("result", "success");
        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3() {
        log.info("ex3");
    }

    @GetMapping("/exView/{id}")
    public void exView(@PathVariable Long id, Model model) {
        log.info("exView with: " + id);
        SampleDTO sampleDto = SampleDTO.builder()
                .sno(id)
                .first("first-" + id)
                .last("last-" + id)
                .regTime(LocalDateTime.now())
                .build();
        model.addAttribute("dto", sampleDto);
    }

    @GetMapping({"/exLayout1", "/exLayout2", "/exTemplate", "/exSidebar"})
    public void exLayout(Model model) {
        log.info("exLayout");
    }
}
