package com.ps.shelter.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

public class DiscountDTO {

    private Long id;

    @Length(max = 8)
    private String code;

    @Max(value = 100)
    private float percentage;

    public DiscountDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
