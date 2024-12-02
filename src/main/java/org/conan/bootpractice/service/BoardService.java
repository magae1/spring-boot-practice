package org.conan.bootpractice.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.conan.bootpractice.domain.PageRequestDTO;
import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.domain.entity.Member;
import org.conan.bootpractice.domain.BoardDTO;
import org.conan.bootpractice.domain.PageResultDTO;
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
        log.info("write: {}", board);
        Board boardEntity = dtoToEntity(board);
        boardRepository.save(boardEntity);
    }

    public Optional<Board> read(Long bno) {
        log.info("read: {}", bno);
        return boardRepository.findById(bno);
    }

    public void modify(BoardDTO board) {
        log.info("modify: {}", board);
        Board boardEntity = dtoToEntity(board);
        boardRepository.save(boardEntity);
    }

    public void remove(Long bno) {
        log.info("remove: {}", bno);
        boardRepository.deleteById(bno);
    }

    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO reqDTO) {
        log.info("getPagedList: {}", reqDTO);
        Pageable pageable = reqDTO.getPageable(Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Function<Object[], BoardDTO> fn = (entities -> {
            return entityToDto((Board) entities[0], (Member) entities[1], (Long) entities[2]);
        });
        return new PageResultDTO<>(result, fn);
    }


    private static BoardDTO entityToDto(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .hit(board.getHit())
                .build();
    }

    private static BoardDTO entityToDto(Board board, Member member, Long replyCount) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue())
                .build();
    }

    private static Board dtoToEntity(BoardDTO boardDto) {
        Member member = Member.builder()
                .email(boardDto.getWriterEmail())
                .build();
        return Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member)
                .build();
    }
}

