package com.group.libraryapp.dto.task.request;

import java.util.List;

public class CalculatorListRequest {
    private List<Integer> numbers;

    public CalculatorListRequest() {}

    public CalculatorListRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
