package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    // private final List<User> users = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    // jdbcTemplate을 생성자에 스프링이 넣어줌
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // -> jdbcTemplate을 이용해 SQL을 날릴 수 있다.
    // -> 생성자를 만들어 jdbcTemplate을 파라미터로 넣으면, 자동으로 들어온다.

    @PostMapping("/user")   // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        //users.add(new User(request.getName(), request.getAge()));

        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // SQL을 만들어서 문자열 변수로 저장
        // 값이 들어가는 부분에 ?를 사용하면, 값을 유동적으로 삽입 가능

        jdbcTemplate.update(sql, request.getName(), request.getAge());
        // 첫 파라미터로는 sql을 받고, ?를 대신할 값을 차례로 삽입
        // jdbcTemplate.update()는 INSERT, UPDATE, DELETE 쿼리에 사용 가능
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        /*
        List<UserResponse> responses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            responses.add(new UserResponse(i + 1, users.get(i)));
        }
        return responses;
        */

        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // mapRow 역할
            // 쿼리의 결과를 받아, 객체를 반환

            // ResultSet에 getType("필드 이름")을 사용해 실제 값을 가져올 수 있다.
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            // UserResponse에 생성자 추가
            return new UserResponse(id, name, age);

        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        // id를 기준으로 유저가 존재하는지 확인하기 위해 SELECT 쿼리 작성
        String readSql = "SELECT * FROM user WHERE id = ?";
        // SQL을 날려 DB에 데이터가 있는지 확인
        // jdbcTemplate.query()의 결과인 List가 비어 있다면, 유저 X
        boolean isUserNotExitst = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if (isUserNotExitst) {                          // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {     // 객체로 만들 수 있지만, 쿼리가 하나이기 때문에 RequestParam 사용
        // name을 기준으로 유저가 존재하는지 확인하기 위해 SELECT 쿼리 작성
        String readSql = "SELECT * FROM user WHERE name = ?";
        // SQL을 날려 DB에 데이터가 있는지 확인
        // jdbcTemplate.query()의 결과인 List가 비어 있다면, 유저 X
        boolean isUserNotExitst = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExitst) {                          // 만약 유저가 존재하지 않는다면
            throw new IllegalArgumentException();       // IllegalArgumentException 던짐
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }



}
