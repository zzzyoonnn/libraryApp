package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookName;

    private boolean isReturn;      // boolean으로 처리하면, tinyint에 잘 매핑된다!

    protected UserLoanHistory() {

    }

    // 대출하면 무조건 is_return = false이므로 제거 가능
    public UserLoanHistory(long user_id, String book_name) {
        this.userId = user_id;
        this.bookName = book_name;
        this.isReturn = false;
    }
}
