package com.group.libraryapp.service.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import com.group.libraryapp.repository.task.FruitRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(JdbcTemplate jdbcTemplate) {
        this.fruitRepository = new FruitRepository(jdbcTemplate);
    }

    public void saveFruit(FruitAddRequest request) {
        fruitRepository.saveFruit(request);
    }

    public void sellFruit(FruitSellRequest request) {
        fruitRepository.sellFruit(request);
    }

    public FruitCostResponse fruitCostResponse(String name) {
        long soldCost = fruitRepository.soldFruitCostResponse(name);
        long unsoldCost = fruitRepository.unsoldFruitCostResponse(name);

        return new FruitCostResponse(soldCost, unsoldCost);
    }
}
