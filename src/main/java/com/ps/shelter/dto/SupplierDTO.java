package com.ps.shelter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class SupplierDTO extends NameIdDTO{

    private  String name;

    private String address;

    @Size(min = 10)
    private String phone;

    @Email
    private String email;

    public SupplierDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
