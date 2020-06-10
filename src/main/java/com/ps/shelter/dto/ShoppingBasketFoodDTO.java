package com.ps.shelter.dto;

public class ShoppingBasketFoodDTO {

    private Long id;

    private Long shoppingBasketId;

    private Long foodId;

    private int quantity;

    public ShoppingBasketFoodDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingBasketId() {
        return shoppingBasketId;
    }

    public void setShoppingBasketId(Long shoppingBasketId) {
        this.shoppingBasketId = shoppingBasketId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
