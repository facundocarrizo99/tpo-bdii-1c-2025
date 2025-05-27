package ecommerce.test;

import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;

public class randomTest {
    public static void main(String[] args) {
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user("default")
                .password("Jlk5vpWwRXa4AF0oPxSNV5OudIUYEL2D")
                .build();

        UnifiedJedis jedis = new UnifiedJedis(
                new HostAndPort("redis-13038.c308.sa-east-1-1.ec2.redns.redis-cloud.com", 13038),
                config
        );

        String res1 = jedis.set("foo", "bar");
        System.out.println(res1); // >>> OK

        String res2 = jedis.get("foo");
        System.out.println(res2); // >>> bar

        jedis.close();
    }
}
