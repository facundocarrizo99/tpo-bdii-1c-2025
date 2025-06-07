package ecommerce.session;

import org.bson.types.ObjectId;

import java.util.*;

public class Cart {
    private final String userId;
    private final List<String> productIds;

    public Cart(String userId, List<String> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }

    public String getUserId() { return userId; }

    public List<String> getProductIds() { return productIds; }

    public String toString() {
        return "Carrito de " + userId + ": " + productIds;
    }
}