package com.board.model.mapper;

import com.board.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<Board> selectBoardList() throws SQLException;

    void insert(Board board) throws SQLException;

    void update(Board board) throws SQLException;

    void delete(int boardNo) throws SQLException;

    void increaseReadCount(int boardNo) throws SQLException;

    Board selectByNo(int boardNo) throws SQLException;

}
