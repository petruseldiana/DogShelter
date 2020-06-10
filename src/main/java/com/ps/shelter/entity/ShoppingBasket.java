package com.ps.shelter.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "s_shopping_basket")
public class ShoppingBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")   //user id avem in baza de date
    private User user;

    @Transient
    private boolean active;

    @Transient
    private String name;

    @OneToMany(mappedBy = "shoppingBasket")
    private List<ShoppingBasketFood> shoppingBasketFoods;

    public ShoppingBasket() {
    }

    public ShoppingBasket(User user, boolean active, String name, List<ShoppingBasketFood> shoppingBasketFoods) {
        this.user = user;
        this.active = active;
        this.name = name;
        this.shoppingBasketFoods = shoppingBasketFoods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingBasketFood> getShoppingBasketFoods() {
        return shoppingBasketFoods;
    }

    public void setShoppingBasketFoods(List<ShoppingBasketFood> shoppingBasketFoods) {
        this.shoppingBasketFoods = shoppingBasketFoods;
    }
}
