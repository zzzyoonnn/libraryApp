package com.group.libraryapp.controller.task;

import com.group.libraryapp.dto.task.request.*;
import com.group.libraryapp.dto.task.response.CalculationResponse;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import com.group.libraryapp.service.task.FruitService;
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

}