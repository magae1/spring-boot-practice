package org.conan.bootpractice.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.conan.bootpractice.domain.entity.Member;
import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.domain.entity.QBoard;


@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoards() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@example.com")
                    .build();
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);
        if (result.isPresent()) {
            Board board = result.get();
            log.info(board);
            log.info(board.getWriter());
        }
    }

    @Test
    public void testReadWithQueryAnnotation1() {
        Long bno = 100L;
        Object result = boardRepository.getBoardWithWriter(bno);
        Object[] arr = (Object[]) result;
        for (Object o : arr) {
            log.info(o.toString());
        }
    }

    @Test
    public void testReadWithQueryAnnotation2() {
        Long bno = 82L;
        List<Object> result = boardRepository.getBoardWithReplies(bno);
        result.forEach(arr ->
                log.info(Arrays.toString(((Object[]) arr))));
    }

    @Test
    public void testReadWithQueryAnnotation3() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.stream().forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testReadWithQueryAnnotation4() {
        Long bno = 96L;
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        log.info(Arrays.toString(arr));
    }

    @Test
    public void testQueryDsl() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending());
        QBoard board = QBoard.board;
        String keyword = "t";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = board.title.contains(keyword);
        builder.and(expression);
        Page<Board> result = boardRepository.findAll(builder, pageable);
        result.stream().forEach(log::info);
    }

    @Test
    public void testQueryDsl2() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending());
        QBoard board = QBoard.board;

        String titleKeyword = "t";
        String contentKeyword = "c";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = board.title.contains(titleKeyword);
        BooleanExpression expression2 = board.content.contains(contentKeyword);
        builder.and(board.bno.gt(2)).and(expression).or(expression2);
        Page<Board> result = boardRepository.findAll(builder, pageable);
        result.stream().forEach(log::info);
    }
}
