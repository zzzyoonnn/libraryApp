package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary        // 우선권을 결정하는 어노테이션
@Repository
public class BookMemoryRepository implements BookRepository {

    // private final List<Book> books = new ArrayList<Book>();

    @Override
    public void saveBook() {
        // books.add(new Book());
    }
}
