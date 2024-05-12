package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookMySqlRepository;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //private final BookRepository bookRepository = new BookMySqlRepository();    // new BookMemoryRepository();

    // Repository에 @Repository 붙인 후
    private final BookRepository bookRepository;

    // 생성자를 통해 BookRepository를 넣어줌
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook() {
        bookRepository.saveBook();
    }
}
