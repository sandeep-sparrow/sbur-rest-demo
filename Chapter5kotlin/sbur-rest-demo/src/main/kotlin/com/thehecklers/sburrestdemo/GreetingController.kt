package com.thehecklers.sburrestdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val greeting: Greeting){

    @GetMapping
    fun getGreeting() = greeting.name

    @GetMapping("/coffee")
    fun getNameAndCoffee() = greeting.coffee

}
