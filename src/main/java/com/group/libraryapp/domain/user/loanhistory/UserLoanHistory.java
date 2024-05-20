package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    //private long userId;
    @ManyToOne      // N : 1 관계
    private User user;

    private String bookName;

    private boolean isReturn;      // boolean으로 처리하면, tinyint에 잘 매핑된다!

    protected UserLoanHistory() {

    }

    // 대출하면 무조건 is_return = false이므로 제거 가능
    public UserLoanHistory(User user, String book_name) {
        this.user = user;
        this.bookName = book_name;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
