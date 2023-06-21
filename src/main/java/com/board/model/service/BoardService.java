package com.board.model.service;

import com.board.model.dto.Board;

import java.util.Map;

public interface BoardService {
    Map<String, Object> list() throws Exception;

    void register(Board board) throws Exception;

    void update(Board board) throws Exception;

    void delete(int boardNo) throws Exception;

    Board detail(int boardNo) throws Exception;

//    Map<String, Object> search(Map<String, Object> searchMap) throws Exception;
//
//    Map<String, Object> search(Map<String, Object> searchMap, int pageNo) throws Exception;
//
//    Map<String, Object> search(Map<String, Object> searchMap, int pageNo, int pageSize) throws Exception;
//
//    Map<String, Object> search(Map<String, Object> searchMap, int pageNo, int pageSize, String sortColumn, String sortOrder) throws Exception;
//
//    Map<String, Object> search(Map<String, Object> searchMap, int pageNo, int pageSize, String sortColumn, String sortOrder, String searchType) throws Exception;
//
//    Map<String, Object> search(Map<String, Object> searchMap, int pageNo, int pageSize, String sortColumn, String sortOrder, String searchType, String searchText) throws Exception;
}
