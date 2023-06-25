package com.thehecklers.sburredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SburRedisApplication {

/*  Replaced RedisOperations with Spring Data Repository Support.
    @Bean
    public RedisOperations<String, Aircraft> redisOperations(RedisConnectionFactory factory){

        Jackson2JsonRedisSerializer<Aircraft> serializer = new Jackson2JsonRedisSerializer<>(Aircraft.class);

        RedisTemplate<String, Aircraft> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(SburRedisApplication.class, args);
    }

}
