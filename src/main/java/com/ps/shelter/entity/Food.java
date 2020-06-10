package com.ps.shelter.entity;

import javax.persistence.*;

@Entity(name = "s_food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int dogAge;

    private String protein;

    private double price;

    private int quantity;

    @OneToOne(mappedBy = "food", cascade = CascadeType.REMOVE)
    private ShoppingBasketFood shoppingBasketFood;

    @ManyToOne
    @JoinColumn(name="supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    public Food() {
    }

    public Food(String type, int dogAge, String protein, double price, int quantity) {
        this.type = type;
        this.dogAge = dogAge;
        this.protein = protein;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
