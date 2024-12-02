package org.conan.bootpractice.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.conan.bootpractice.domain.entity.Board;
import org.conan.bootpractice.domain.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReplies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Long bno = (long) (Math.random() * 100) + 1;
            Board board = Board.builder()
                    .bno(bno)
                    .build();
            Reply reply = Reply.builder()
                    .reply("reply..." + i)
                    .replyer("guest..." + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }

    @Transactional
    @Test
    public void readReply1() {
        Optional<Reply> result = replyRepository.findById(2L);
        if (result.isPresent()) {
            Reply reply = result.get();
            log.info(reply);
            log.info(reply.getBoard());
        }
    }

}
