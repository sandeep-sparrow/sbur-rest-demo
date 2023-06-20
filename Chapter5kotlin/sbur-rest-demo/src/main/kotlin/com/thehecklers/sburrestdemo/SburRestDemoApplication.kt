package com.thehecklers.sburrestdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@SpringBootApplication
@ConfigurationPropertiesScan
class SburRestDemoApplication

fun main(args: Array<String>) {
    runApplication<SburRestDemoApplication>(*args)
}

@Component
class DataLoader(private val coffeeRepository: CoffeeRepository) {
    @PostConstruct
    private fun loadData() = coffeeRepository.saveAll(
        listOf(
            Coffee("Café Cereza"),
            Coffee("Café Ganador"),
            Coffee("Café Lareño"),
            Coffee("Café Três Pontas")
        )
    )
}



