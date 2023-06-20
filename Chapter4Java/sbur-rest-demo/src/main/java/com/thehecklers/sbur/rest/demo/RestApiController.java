package com.thehecklers.sbur.rest.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/coffees")
public class RestApiController {

    private final ICoffeeRepository iCoffeeRepository;
    // private List<Coffee> coffees = new ArrayList<>();

    public RestApiController(ICoffeeRepository iCoffeeRepository){
        this.iCoffeeRepository = iCoffeeRepository;
    }

    @GetMapping
    public Iterable<Coffee> getCoffees(){
        // return coffees;
        return iCoffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id){

/*  Initial Commit: Chapter 3
        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                return Optional.of(c);
            }
        }
*/
        return iCoffeeRepository.findById(id);
        // return Optional.empty();
    }

    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        // coffees.add(coffee);
        iCoffeeRepository.save(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@RequestBody Coffee coffee, @PathVariable("id") String id){

/* Initial Commit: Chapter 3
        int coffeeIndex = -1;
        for(Coffee c: coffees){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                                     new ResponseEntity<>(coffee, HttpStatus.OK);
*/
        return (iCoffeeRepository.existsById(id)) ? new ResponseEntity<>(iCoffeeRepository.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>(iCoffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id){
        // coffees.removeIf(c -> c.getId().equals(id));
        iCoffeeRepository.deleteById(id);
    }

}
