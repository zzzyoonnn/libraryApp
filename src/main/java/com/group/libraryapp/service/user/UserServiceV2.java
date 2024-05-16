package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 저장 기능
    // save메소드에 객체를 넣어주면 INSERT SQL이 자동으로 날라간다.
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }
    // save가 되고 난 후의 User는 id가 들어 있다.

    // 유저 조회 가능
    // findAll을 사용하면 모든 데이터를 가져온다. -> select * from user;
    // UserResponse에 생성자를 추가하면 코드가 깔끔해진다.
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }


    // 유저 업데이트 기능
    public void updateUser(UserUpdateRequest request) {
        // userRepository에 user가 없다면 Optional의 orElseThrow를 사용해 예외를 던진다.
        // findById : select * from user where id = ?;
        // 결과 : Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        // 객체를 업데이트 해주고, save메소드를 호출한다.
        // 그러면 자동으로 UPDATE SQL이 날라가게 된다.
        user.updateName(request.getName());
        userRepository.save(user);
    }

    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        // 주어지는 데이터를 DB에서 제거한다.
        userRepository.delete(user);
    }


}
