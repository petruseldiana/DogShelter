package com.ps.shelter.entity;

import javax.persistence.*;

@Entity(name = "s_shopping_basket_food")
public class ShoppingBasketFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="shopping_basket_id", referencedColumnName = "id")
    private ShoppingBasket shoppingBasket;

    @OneToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    private int quantity;

    public ShoppingBasketFood() {
    }

    public ShoppingBasketFood(ShoppingBasket shoppingBasket, Food food, int quantity) {
        this.shoppingBasket = shoppingBasket;
        this.food = food;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
