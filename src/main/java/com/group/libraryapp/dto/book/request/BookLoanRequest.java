package com.group.libraryapp.dto.book.request;

import org.springframework.stereotype.Repository;

@Repository
public class BookLoanRequest {

    private String userName;
    private String bookName;

    public String getUserName() {
        return userName;
    }

    public String getBookName() {
        return bookName;
    }
}
