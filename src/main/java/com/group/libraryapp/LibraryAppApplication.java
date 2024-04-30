package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 어노테이션
@SpringBootApplication
// 스프링을 실행시킬 때 필요한 다양한 설정들을 자동으로 수행
public class LibraryAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args);
    // 실제 SpringApplication을 실행
  }

}
