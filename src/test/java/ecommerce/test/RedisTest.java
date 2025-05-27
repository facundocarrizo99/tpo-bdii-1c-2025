package ecommerce.test;

import ecommerce.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.UnifiedJedis;

public class RedisTest {
    public static void main(String[] args) {
        try {
            UnifiedJedis jedis = RedisConfig.getJedis();
            jedis.set("test", "ok");
            String value = jedis.get("test");
            System.out.println("✅ Redis respondió: " + value);
        } catch (Exception e) {
            System.out.println("❌ Error conectando a Redis:");
            e.printStackTrace();
        }
    }
}
