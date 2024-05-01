package com.group.libraryapp.dto.task.request;

import java.util.List;

public class CalculatorListRequest {
    private List<Integer> numbers;

    public CalculatorListRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getList() {
        return numbers;
    }

    public void setList(List<Integer> list) {
        this.numbers = list;
    }
}
