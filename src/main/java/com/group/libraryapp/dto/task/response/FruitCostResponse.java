package com.group.libraryapp.dto.task.response;

public class FruitCostResponse {

    private long soldCost;
    private long unsoldCost;

    public FruitCostResponse(long soldCost, long unsoldCost) {
        this.soldCost = soldCost;
        this.unsoldCost = unsoldCost;
    }

    public long getSoldCost() {
        return soldCost;
    }

    public long getUnsoldCost() {
        return unsoldCost;
    }
}
