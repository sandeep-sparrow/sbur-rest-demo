package com.thehecklers.sbur.rest.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    private final ICoffeeRepository iCoffeeRepository;

    public DataLoader(ICoffeeRepository iCoffeeRepository) {
        this.iCoffeeRepository = iCoffeeRepository;
    }

    @PostConstruct
    private void loadData(){
        this.iCoffeeRepository.saveAll(List.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cate Tres Pont as")
        ));
    }
}
