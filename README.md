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
## 4. HTTP 통신 확인
### **localhost/board** (POST) - 글작성
![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/b838668e-bf26-4fd0-a91b-b9575b95c42f)
### **localhost/board** (PUT) - 글수정
![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/50c2a468-8a66-43fe-9cca-f72bdf9b4538)
### **localhost/board** (GET) - 글목록
![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/310a5860-e8a0-46b4-a354-704a1a99fe95)
### **localhost/board/1** (GET) - 글조회
![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/0861c1de-44fc-4f98-8207-a93c00169615)
### **localhost/board/2** (DELETE) - 글삭제
![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/c3e037f7-0a9a-468e-af41-acc528830aa7)


