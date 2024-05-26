package com.group.libraryapp.domain.book;


import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)       // name의 길이가 255이기 때문에 length 생략 가능
    private String name;

    // 기본 생성자
    protected Book() {

    }

    public Book(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
