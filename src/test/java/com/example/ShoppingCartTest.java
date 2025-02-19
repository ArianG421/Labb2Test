package com.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest {

    @Test
    void addItem_ShouldAddItemToCart() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 1.0);

        // Act
        cart.addItem(item, 2);

        // Assert
        assertThat(cart.getTotalItems()).isEqualTo(2);
        assertThat(cart.getTotalPrice()).isEqualTo(2.0);
    }


}
