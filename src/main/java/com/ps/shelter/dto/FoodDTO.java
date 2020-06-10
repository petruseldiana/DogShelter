package com.ps.shelter.dto;

import org.hibernate.validator.constraints.NotBlank;

public class FoodDTO{

    private Long id;

    @NotBlank(message = "Food type is mandatory")
    private String type;

    private int dogAge;

    @NotBlank(message = "Protein is mandatory")
    private String protein;

    private double price;

    private int quantity;

    private NameIdDTO supplier;

    public FoodDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameIdDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(NameIdDTO supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
