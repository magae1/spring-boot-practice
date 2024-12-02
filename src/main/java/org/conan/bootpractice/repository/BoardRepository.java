package org.conan.bootpractice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import org.conan.bootpractice.domain.entity.Board;


public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    @Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno=:bno")
    List<Object> getBoardWithReplies(@Param("bno") Long bno);

    @Query(value =
            "SELECT b, w, COUNT(r) " +
                    "FROM Board b " +
                    "LEFT JOIN b.writer w " +
                    "LEFT JOIN Reply r on r.board = b " +
                    "GROUP BY b",
            countQuery =
                    "SELECT COUNT(b) " +
                            "FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("SELECT b, w, COUNT(r) " +
            "FROM Board b " +
            "LEFT JOIN b.writer w " +
            "LEFT OUTER JOIN Reply r ON r.board = b " +
            "WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
