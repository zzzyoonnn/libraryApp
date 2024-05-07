package com.group.libraryapp.dto.task.request;

public class FruitAddRequest {

    private long id;
    private String name;
    private String warehousingDate;
    private long price;
    private boolean saleStatus;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWarehousingDate() {
        return warehousingDate;
    }

    public long getPrice() {
        return price;
    }

    public boolean isSaleStatus() {
        return saleStatus;
    }
}
