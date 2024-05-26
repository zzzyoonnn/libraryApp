package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {

    @Id         // PRIMARY KEY. import할 때, javax.persistence로 import 하기
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // AUTO_INCREMENT
    private Long id = null;     // bigint -> long

    // User객체의 name에 적용
    @Column(nullable = false, length = 20, name = "name")       // name varchar(20)
    private String name;
    private Integer age;        // 테이블과 동일하기 때문에 @Column 어노테이션 생략 가능

    // 1 : N 관계(이 부분을 지운다면 단방향으로 @ManyToOne 사용)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    // 대출 기능 리팩토링
    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    // 반납 기능 리팩토링
    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()         // 함수형 프로그래밍 할 수 있게 stream 시작
                .filter(history -> history.getBookName().equals(bookName))      // filter : 들어오는 객체즐 중, 다음 조건을 충족하는 것만 필터링
                .findFirst()                                                    // 첫 번째로 해당하는 UserLoanHistory 찾기
                .orElseThrow(IllegalArgumentException::new);                    // 없다면 IllegalArgumentException 발생

        targetHistory.doReturn();       // doReturn을 통해 반납 처리
    }
}
