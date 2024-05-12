package com.group.libraryapp.config;

import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@Configurable
public class UserConfiguration {

//    @Bean
//    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
//        return new UserRepository(jdbcTemplate);
//    }
}
