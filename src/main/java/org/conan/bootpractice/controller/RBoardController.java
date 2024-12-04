package org.conan.bootpractice.controller;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.conan.bootpractice.domain.BoardDTO;
import org.conan.bootpractice.domain.BoardPageRequestDTO;
import org.conan.bootpractice.domain.BoardPageResultDTO;
import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.service.BoardService;


@Log4j2
@RestController
@RequestMapping("/board")
@AllArgsConstructor
public class RBoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public BoardPageResultDTO<BoardDTO, Object[]> list(BoardPageRequestDTO pageRequestDTO) {
        log.info("Board list");
        return boardService.getList(pageRequestDTO);
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
