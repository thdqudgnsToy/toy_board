package com.board.model.service;

import com.board.model.dto.Board;
import com.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public Map<String, Object> list() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("list", boardMapper.selectBoardList());
        return map;
    }

    @Override
    public void register(Board board) throws Exception {
        boardMapper.insert(board);
    }

    @Override
    public void update(Board board) throws Exception {
        boardMapper.update(board);
    }

    @Override
    public void delete(int boardNo) throws Exception {
        boardMapper.delete(boardNo);
    }

    @Override
    public Board detail(int boardNo) throws Exception {
        boardMapper.increaseReadCount(boardNo);
        return boardMapper.selectByNo(boardNo);
    }
}
