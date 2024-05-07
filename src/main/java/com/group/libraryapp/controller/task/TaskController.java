package com.group.libraryapp.controller.task;

import com.group.libraryapp.dto.task.request.*;
import com.group.libraryapp.dto.task.response.CalculationResponse;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private final JdbcTemplate jdbcTemplate;

    public TaskController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/v1/calc")
    public CalculationResponse calculateNumbers(CalculationRequest request) {
        return new CalculationResponse(request);
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DayOfTheWeekRequest getDayOfTheWeek(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return new DayOfTheWeekRequest(localDate);
    }

    @PostMapping("/api/v1/add-numbers")
    public int addNumbers(@RequestBody CalculatorListRequest request) {
        List<Integer> numbers = request.getNumbers();
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    @PostMapping("/api/v1/fruit")
    public  void saveFruit(@RequestBody FruitAddRequest request) {
        String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellRequest request) {
        String sql = "UPDATE fruit SET saleStatus = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitCostResponse fruitCostResponse(@RequestParam String name) {
        String saleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 1 GROUP BY name HAVING name = ?";
        String notSaleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 0 GROUP BY name HAVING name = ?";

        //  sql 쿼리를 실행하여 결과를 long 데이터 타입으로 변환하여 받기 위해 long.class 작성
        long soldCost = jdbcTemplate.queryForObject(saleSql, long.class, name);
        long unsoldCost = jdbcTemplate.queryForObject(notSaleSql, long.class, name);

        return new FruitCostResponse(soldCost, unsoldCost);
    }
}