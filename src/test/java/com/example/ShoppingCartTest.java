package com.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest {

    @Test
    void addItem_ShouldAddItemToCart() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);


        cart.addItem(item, 2);


        assertThat(cart.getTotalItems()).isEqualTo(2);
        assertThat(cart.getTotalPrice()).isEqualTo(2.0);
    }

    @Test
    void removeItem_ShouldRemoveItemFromCart() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);
        cart.addItem(item, 2);

        cart.removeItem(item);

        assertThat(cart.getTotalItems()).isEqualTo(0);
        assertThat(cart.getTotalPrice()).isEqualTo(0.0);
    }

    @Test
    void applyDiscount_ShouldReduceTotalPrice() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);
        cart.addItem(item, 10);

        // 10% discount
        cart.applyDiscount(0.1);


        assertThat(cart.getTotalPrice()).isEqualTo(9.0);
    }

    @Test
    void updateQuantity_ShouldUpdateItemQuantity() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);
        cart.addItem(item, 2);


        cart.updateQuantity(item, 5);


        assertThat(cart.getTotalItems()).isEqualTo(5);
        assertThat(cart.getTotalPrice()).isEqualTo(5.0);
    }

    @Test
    void addItem_WithZeroQuantity_ShouldNotAddItem() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);

        // Quantity is zero.
        cart.addItem(item, 0);


        assertThat(cart.getTotalItems()).isEqualTo(0);
        assertThat(cart.getTotalPrice()).isEqualTo(0.0);
    }

    @Test
    void applyDiscount_WithZeroDiscount_ShouldNotChangeTotalPrice() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);
        cart.addItem(item, 10);

        // 0% discount
        cart.applyDiscount(0.0);


        assertThat(cart.getTotalPrice()).isEqualTo(10.0);
    }

    @Test
    void removeItem_NotInCart_ShouldDoNothing() {

        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);

        //No item
        cart.removeItem(item);


        assertThat(cart.getTotalItems()).isEqualTo(0);
        assertThat(cart.getTotalPrice()).isEqualTo(0.0);
    }



}
