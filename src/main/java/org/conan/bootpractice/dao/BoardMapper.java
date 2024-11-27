package org.conan.bootpractice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.conan.bootpractice.domain.Board;

@Mapper
public interface BoardMapper {
    //    @Select("SELECT * FROM BOARD WHERE BNO > 0")
    public List<Board> getBoardList();

    public int insert(Board board);

    public int insertSelectKey(Board board);

    public Board read(int bno);

    public int increaseHit(int bno);

    public int delete(int bno);

    public int update(Board board);
}
