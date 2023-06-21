package com.board.controller;

import com.board.model.dto.Board;
import com.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * Restful API
     * 목록: @GetMapping /board
     * 작성: @PostMapping /board
     * 수정: @PutMapping /board
     * 삭제: @DeleteMapping /board/:no
     * 상세: @GetMapping /board/:no
     */

    @GetMapping
    public Map<String, Object> list() throws Exception {
        return boardService.list();
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Board board) throws Exception  {
        boardService.register(board);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Board board) throws Exception  {
        boardService.update(board);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<?> delete(@PathVariable int boardNo) throws Exception  {
        boardService.delete(boardNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{boardNo}")
    public Board detail(@PathVariable int boardNo) throws Exception {
        return boardService.detail(boardNo);
    }

}
