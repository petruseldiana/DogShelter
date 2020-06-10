package com.ps.shelter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "s_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    private String startHour;

    private String stopHour;

    private String details;

    public Schedule() {
    }

    public Schedule(String day, String startHour, String stopHour, String details) {
        this.day = day;
        this.startHour = startHour;
        this.stopHour = stopHour;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
}
