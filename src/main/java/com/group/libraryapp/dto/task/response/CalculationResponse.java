package com.group.libraryapp.dto.task.response;

import com.group.libraryapp.dto.task.request.CalculationRequest;

public class CalculationResponse {
    private int plus;
    private int minus;
    private int multiply;

    public CalculationResponse(CalculationRequest request) {
        this.plus = request.getNum1() + request.getNum2();
        this.minus = request.getNum1() - request.getNum2();
        this.multiply = request.getNum1() * request.getNum2();
    }

    public int getPlus() {
        return plus;
    }

    public int getMinus() {
        return minus;
    }

    public int getMultiply() {
        return multiply;
    }
}
