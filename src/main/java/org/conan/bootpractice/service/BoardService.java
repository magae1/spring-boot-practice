package org.conan.bootpractice.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import org.conan.bootpractice.domain.Board;
import org.conan.bootpractice.domain.BoardDTO;
import org.conan.bootpractice.repository.BoardRepository;


@Log4j2
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        log.info("getAllBoards");
        return boardRepository.findAll();
    }

    public void write(BoardDTO board) {
        log.info("write: " + board);
        Board boardEntity = dtoToEntity(board);
        boardRepository.save(boardEntity);
    }

    public Optional<Board> read(Long bno) {
        log.info("read: " + bno);
        return boardRepository.findById(bno);
    }

    public void modify(BoardDTO board) {
        log.info("modify: " + board);
        Board boardEntity = dtoToEntity(board);
        boardRepository.save(boardEntity);
    }

    public void remove(Long bno) {
        log.info("remove: " + bno);
        boardRepository.deleteById(bno);
    }

    private static Board dtoToEntity(BoardDTO boardDto) {
        return Board.builder()
                .bno(boardDto.getBno())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .hit(boardDto.getHit())
                .build();
    }
}

