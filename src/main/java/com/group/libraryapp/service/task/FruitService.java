package com.group.libraryapp.service.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import com.group.libraryapp.repository.task.FruitRepository;
import org.springframework.stereotype.Service;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitAddRequest request) {
        fruitRepository.saveFruit(request);
    }

    public void sellFruit(FruitSellRequest request) {
        fruitRepository.sellFruit(request);
    }

    public FruitCostResponse fruitCostResponse(String name) {
        return fruitRepository.fruitCostResponse(name);
    }
}
