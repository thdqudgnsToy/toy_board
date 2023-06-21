# Restful API 게시판
REST란, HTTP URI를 통해 자원을 명시하고, HTTP Method를 통해 해당 자원에 대한 CRUD를 적용하는 것을 의미합니다. 이번 토이 프로젝트는 Java / MVC2패턴 / SpringBoot / REST 복습을 목표로 진행하였습니다.   

게시판의 경우 **board** 라는 자원명에 GET/POST/PUT/DELETE HTTP Method 을 더하여 동작을 구분하였습니다.
- **localhost/board** (POST) - 글작성
- **localhost/board** (PUT) - 글수정
- **localhost/board** (GET) - 글목록
- **localhost/board/:no** (GET) - 글조회
- **localhost/board/:no** (DELETE) - 글삭제

작업 환경은 다음과 같습니다.
- Tool: IntelliJ Ultimate / MySQL Workbench / Talend API Tester
- Version: JDK 11 / Spring Boot 2.7.12 (Maven) / MySQL 8.0.32
- 시간: 대락 5시간

## 1. 프로젝트 생성 및 초기 오류 해결

먼저 start.spring.io 사이트를 통해 간편하게 프로젝트를 생성합니다.
- 간단한 프로젝트이기에 Maven으로 생성하였습니다. 속도, 성능, 가독성을 고려한다면 Gradle을 추천합니다.
- Spring Boot 3부터는 JDK 17이 필수입니다. 저는 JDK 11로 진행할 것이기에 2.X 버전을 사용하였습니다.
- Web 프로그램을 만들 것이므로 WAR가 적합해 보이지만, Spring Boot의 표준은 JAR이므로 WAR이 아닌 JAR을 사용하였습니다.
- 그 외에 기본 의존성들을 이미지와 같이 주었습니다.


![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/83c5a7e7-1532-4053-9ba7-7d1daae75f17)

이렇게 설정한 다음에 바로 `@SpringBootApplication` 가 붙어있고,   
main메소드가 있는 어플리케이션 클래스를 실행하니   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/4c376037-2a5a-4825-91d6-62f5baf4468a)
WARN 경고를 보면, `No MyBatis mapper was found in ~` 이런 메세지가 나옵니다. MyBatis mapper 설정을 하지 않아서 발생하는 에러 같으므로 mapper를 설정해줍니다. mapper를 설정하기 전에, MySQL DB 계정을 만들어줍니다. 도움받은 [URL](https://clgnsdl94.tistory.com/49)입니다.   

계정을 생성할 때 DBA 권한을 부여하고, 비밀번호를 8자 이상으로 설정해주어야 Java에서 DB로 SQL문을 돌리기 위해 접속할 때 추후 문제가 발생하지 않습니다. 저는 처음에 비밀번호를 0000으로 해서 그런지 게시글 삽입이나 목록 조회등 SQL쿼리를 날릴 때 계정 문제로 오류가 발생했습니다. 비밀번호를 바꿔주니 문제가 해결되었습니다.   

계정을 만들었으면, 본격젹으로 Java Project와 DB를 `application.yml` 파일을 통해 연결시켜줍니다.   
그리고 문제가 되었던 Mybatis mapper 설정을 12~14 행에서 해주었습니다.   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/fdf4aaa7-bd15-494a-bf5e-08498610dc7c)
위 사진에 보면, datasource 밑에 hikari가 있는데요. 저것 때문에 문제가 생겼습니다. 이것은 조금 더 아래에서 다루겠습니다.   

맨 처음의 문제가 mapper 설정이 없기 때문에 발생한 문제였습니다. 이를 해결하기 위해 프로젝트의 틀을 잡아줍니다.   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/1b29d4e1-6dac-4880-8b18-e6a8a23b7745)

틀을 잡아 줌으로서 `MyBatis mapper` 문제는 사라졌지만,   
dataSource 라는 Bean이 생성되지 않았다는 오류가 나옵니다. 이것이 위에서 말했던 hikari문제 입니다.   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/9f52a76c-3c30-4314-8853-1e8d3c25097f)

hikari를 없앰으로서 문제가 해결되었습니다.   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/bfce3c6b-b14b-4cc2-8f08-848551477fb3)
Spring Boot 2.X 부터는 Hikari가 Connection Pooling의 기본값입니다. 그렇기 때문에 Spring Boot 2.X 부터는 Driver 설정 정보를 적을 때 datasource 뒤에 hikari를 적어주지 않아도 됩니다. hikari를 사용할 수도 있는데, 이때는 auto-commit이나 pool관련 설정을 하기 위해 hikari를 작성합니다. [관련 URL](https://girinprogram93.tistory.com/46)   

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/7b3bfe03-31cd-4ebe-b192-5dc07d006805)
아무 문제없이 정상 작동되었습니다. 다만, 실행 이후 Java가 DB와 실제로 통신하는 시점에 문제가 생기지 않게 하기 위해 의존성을 2개만 추가합니다.

![image](https://github.com/thdqudgnsToy/toy_board/assets/92148521/62808dbb-d888-4c5b-8ebc-91e935fa1969)




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

## 3. BackEnd 코드 입력
  - [Controller](https://github.com/thdqudgnsToy/toy_board/blob/main/src/main/java/com/board/controller/BoardController.java)
  - [Service](https://github.com/thdqudgnsToy/toy_board/tree/main/src/main/java/com/board/model/service)
  - [Mapper](https://github.com/thdqudgnsToy/toy_board/tree/main/src/main/resources/mapper/board.xml)

## 4. HTTP 통신 Test
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


---

그 외 참조
[Maven과 Gradle의 개념 및 비교](https://dev-coco.tistory.com/65)
[JAR과 WAR의 차이는 무엇일까?](https://wonin.tistory.com/498)
