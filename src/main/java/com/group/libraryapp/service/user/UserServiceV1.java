package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service        // UserService를 스프링 빈으로 등록
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    // Controller가 객체로 변환한 것을 받을 것이기 때문에 UserUpdateRequest 객체만 받음
    public void updateUser(UserUpdateRequest request) {
        boolean isUserNotExitst = userJdbcRepository.isUserNotExist(request.getId());

        if (isUserNotExitst) {                          // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExit(name)) {   // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        userJdbcRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

}
