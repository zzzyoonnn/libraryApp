package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class BookMySqlRepository implements BookRepository {

    @Override       // 단축키 : ctrl + O
    public void saveBook() {

    }
}
