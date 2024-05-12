package com.group.libraryapp.controller.book;

import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    // private final BookService bookService = new BookService();
    // Service에 @Service 붙인 후
    private final BookService bookService;

    // 생성자를 통해 Controller가 대신 Service를 넣어줌
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }
}
