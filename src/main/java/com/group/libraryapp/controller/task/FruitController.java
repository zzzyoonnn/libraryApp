package com.group.libraryapp.controller.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import com.group.libraryapp.service.task.FruitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public  void saveFruit(@RequestBody FruitAddRequest request) {
        fruitService.saveFruit(request);
    }


    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellRequest request) {
        fruitService.sellFruit(request);
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitCostResponse fruitCostResponse(@RequestParam String name) {
        return fruitService.fruitCostResponse(name);
    }
}
