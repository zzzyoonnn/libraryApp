package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository를 붙이지 않고 JpaRepository를 상속받는 것만으로도 스프링 빈으로 관리됨
public interface UserRepository extends JpaRepository<User, Long> {     // User : Entity 객체, Long : User의 id

}
