package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// @Repository를 붙이지 않고 JpaRepository를 상속받는 것만으로도 스프링 빈으로 관리됨
public interface UserRepository extends JpaRepository<User, Long> {     // User : Entity 객체, Long : User의 id
    Optional<User> findByName(String name);
    // 반환 타입 : User
    // 유저가 없다면, null 반환
    // 함수 이름만 작성하면, 알아서 SQL이 조립됨
    // find : 1개의 데이터만 가져옴
    // by : 뒤에 붙는 필드 이름으로 SELECT쿼리의 WHERE문이 작성됨
    // findByName : SELECT * FROM user WHERE name = ?;

    boolean existsByName(String name);

}




