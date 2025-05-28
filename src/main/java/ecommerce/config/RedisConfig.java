package ecommerce.config;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;

public class RedisConfig {
    static String host = AppConfig.get("redis.host");
    static int port = Integer.parseInt(AppConfig.get("redis.port"));
    static String password = AppConfig.get("redis.password");

    static JedisClientConfig config = DefaultJedisClientConfig.builder()
            .user("default")
            .password(password)
            .build();

    static UnifiedJedis jedis = new UnifiedJedis(
            new HostAndPort(host, port),
            config
    );


    public static UnifiedJedis getJedis() {
        return jedis;
    }
}

