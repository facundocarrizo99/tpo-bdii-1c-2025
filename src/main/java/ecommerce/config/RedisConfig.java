package ecommerce.config;

public class RedisConfig {
    private static final Jedis jedis = new Jedis(AppConfig.get("redis.host"),
            Integer.parseInt(AppConfig.get("redis.port")));

    public static Jedis getJedis() {
        return jedis;
    }
}