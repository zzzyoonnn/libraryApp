package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }

    // Controller가 객체로 변환한 것을 받을 것이기 때문에 UserUpdateRequest 객체만 받음
    public void updateUser(UserUpdateRequest request) {
        boolean isUserNotExitst = userRepository.isUserNotExitst(request.getId());

        if (isUserNotExitst) {                          // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExit(name)) {                          // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        userRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

}
