package com.thehecklers.sbur.rest.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffees")
public class RestApiController {

    private List<Coffee> coffees = new ArrayList<>();

    public RestApiController(){
        coffees.addAll(List.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cate Tres Pont as")
        ));
    }

    @GetMapping
    public Iterable<Coffee> getCoffees(){
        return coffees;
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id){
        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@RequestBody Coffee coffee, @PathVariable("id") String id){
        int coffeeIndex = -1;
        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                                     new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }

}
