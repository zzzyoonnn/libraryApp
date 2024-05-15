package com.group.libraryapp.repository.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import com.group.libraryapp.dto.task.response.FruitCostResponse;

public interface FruitRepository {
    void saveFruit(FruitAddRequest request);

    void sellFruit(FruitSellRequest request);

    FruitCostResponse fruitCostResponse(String name);
}
