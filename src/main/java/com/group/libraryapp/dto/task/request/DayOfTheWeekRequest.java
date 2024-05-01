package com.group.libraryapp.dto.task.request;

import java.time.LocalDate;

public class DayOfTheWeekRequest {
    private  String dayOfTheWeek;

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public DayOfTheWeekRequest(LocalDate date) {
        String day = date.getDayOfWeek().toString();
        this.dayOfTheWeek = day.substring(0, day.length() - 3);
    }
}
