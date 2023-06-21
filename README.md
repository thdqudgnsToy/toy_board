# Restful API 게시판

## 1. 프로젝트 생성 및 초기 오류 해결
## 2. DB 생성 및 DTO 생성

  - SQL:
    ```sql
    create table board (
    board_no int auto_increment,
      title varchar(30) not null,
      content text not null,
      writer varchar(20) not null,
      view_cnt int default 0,
      create_date datetime default now(),
      update_date datetime default null,
      primary key (board_no)
    );
    ```
  - [DTO](https://github.com/thdqudgnsToy/toy_board/blob/main/src/main/java/com/board/model/dto/Board.java)
## 3. MVC 코드 입력

  - [Controller](https://github.com/thdqudgnsToy/toy_board/blob/main/src/main/java/com/board/controller/BoardController.java)
  - [Service](https://github.com/thdqudgnsToy/toy_board/tree/main/src/main/java/com/board/model/service)
  - [Mapper](https://github.com/thdqudgnsToy/toy_board/tree/main/src/main/resources/mapper/board.xml)
