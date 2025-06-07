package ecommerce.session;

import ecommerce.config.RedisConfig;
import org.bson.types.ObjectId;
import redis.clients.jedis.UnifiedJedis;

import java.util.*;

public class SessionService {
    private final UnifiedJedis jedis = RedisConfig.getJedis();

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