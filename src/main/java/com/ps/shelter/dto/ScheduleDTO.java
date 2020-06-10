package com.ps.shelter.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ScheduleDTO {

    private Long id;

    @NotBlank(message = "Day is mandatory")
    private String day;

    private String startHour;

    private String stopHour;

    private String details;

    public ScheduleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStopHour() {
        return stopHour;
    }

    public void setStopHour(String stopHour) {
        this.stopHour = stopHour;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
