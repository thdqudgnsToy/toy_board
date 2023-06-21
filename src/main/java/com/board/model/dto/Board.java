package com.board.model.dto;

import java.util.Objects;

public class Board {

    private Integer boardNo;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private String createDate;
    private String updateDate;

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardNo=" + boardNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", viewCnt=" + viewCnt +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(boardNo, board.boardNo) && Objects.equals(title, board.title) && Objects.equals(content, board.content) && Objects.equals(writer, board.writer) && Objects.equals(viewCnt, board.viewCnt) && Objects.equals(createDate, board.createDate) && Objects.equals(updateDate, board.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardNo, title, content, writer, viewCnt, createDate, updateDate);
    }
}
