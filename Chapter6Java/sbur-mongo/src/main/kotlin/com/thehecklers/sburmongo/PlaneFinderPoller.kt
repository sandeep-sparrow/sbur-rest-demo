package com.thehecklers.sburmongo

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

@Component
@EnableScheduling
class PlaneFinderPoller(private val aircraftRepository: AircraftRepository) {

    private val client =
        WebClient.create("http://localhost:7634/aircraft")

    @Scheduled(fixedRate = 1000)
    private fun pollPlanes(){
        // aircraftRepository.deleteAll();

        client.get()
            .retrieve()
            .bodyToFlux<Aircraft>()
            .filter{!it.reg.isNullOrEmpty()}
            .toStream()
            .forEach { aircraftRepository.save(it) }

        println("--- All Aircraft ---")
        aircraftRepository.findAll().forEach { println(it) }
    }
}
