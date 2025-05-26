package ecommerce.session;

import redis.clients.jedis.Jedis;

import java.util.*;

public class SessionService {
    private final Jedis jedis = new Jedis("localhost");

    public void addToCart(String userId, String productId) {
        jedis.rpush("cart:" + userId, productId);
    }

    public Cart getCart(String userId) {
        List<String> items = jedis.lrange("cart:" + userId, 0, -1);
        return new Cart(userId, items);
    }

    public void clearCart(String userId) {
        jedis.del("cart:" + userId);
    }
}