package com.thehecklers.sburredis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@EnableScheduling
@Component
public class PlaneFinderPoller {

    private final WebClient client = WebClient.create("http://localhost:7634/aircraft");

    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Aircraft> redisOperations;

    public PlaneFinderPoller(RedisConnectionFactory connectionFactory,
                             RedisOperations<String, Aircraft> redisOperations){
        this.connectionFactory = connectionFactory;
        this.redisOperations = redisOperations;
    }

    @Scheduled(fixedRate = 10000)
    private void pollPlanes(){

        // This part of code remove all the data from the redis server
        connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(ac -> redisOperations.opsForValue().set(ac.getReg(), ac));

        Objects.requireNonNull(redisOperations.opsForValue()
                        .getOperations()
                        .keys("*"))
                .forEach(ac ->
                        System.out.println(redisOperations.opsForValue().getAndPersist(ac)));

        // connectionFactory.getConnection().serverCommands().save();

    }

}
