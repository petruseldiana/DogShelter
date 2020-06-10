package com.ps.shelter.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DogUpdateDTO {

    @NotBlank(message = "Status is mandatory")
    private String status;

    public DogUpdateDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
