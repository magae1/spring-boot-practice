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

import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.domain.BoardDTO;
import org.conan.bootpractice.service.BoardService;


@Log4j2
@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("Board list");
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/write")
    public void write(Model model) {
    }

    @PostMapping("/write")
    public String register(BoardDTO board, RedirectAttributes rattr) {
        log.info("Board register");
        boardService.write(board);
        rattr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("bno") Long bno, Model model) {
        Optional<Board> result = boardService.read(bno);
        result.ifPresent(board -> model.addAttribute("board", board));
    }

    @PostMapping("/modify")
    public String modify(BoardDTO board) {
        log.info("Board modify: " + board);
        boardService.modify(board);
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno) {
        log.info("Board remove: " + bno);
        boardService.remove(bno);
        return "redirect:/board/list";
    }

}
