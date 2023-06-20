package com.thehecklers.sburrestdemo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/coffees")
class RestApiDemoController(private val coffeeRepository: CoffeeRepository) {

    @GetMapping
    fun getCoffees() = coffeeRepository.findAll()

    @GetMapping("/{id}")
    fun getCoffeeById(@PathVariable id: String) = coffeeRepository.findById(id)

    @PostMapping
    fun postCoffee(@RequestBody coffee: Coffee) = coffeeRepository.save(coffee)

    @PutMapping("/{id}")
    fun putCoffee(
        @PathVariable id: String,
        @RequestBody coffee: Coffee
    ): ResponseEntity<Coffee> {
        return if (coffeeRepository.existsById(id))
            ResponseEntity(coffeeRepository.save(coffee), HttpStatus.OK)
        else
            ResponseEntity(coffeeRepository.save(coffee), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteCoffee(@PathVariable id: String) = coffeeRepository.deleteById(id)
}

