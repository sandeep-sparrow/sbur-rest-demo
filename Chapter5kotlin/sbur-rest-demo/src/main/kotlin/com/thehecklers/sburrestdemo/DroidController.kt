package com.thehecklers.sburrestdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/droid")
class DroidController(private val droid: Droid) {

    @GetMapping
    fun getDroidInformation() = "droid id: ${droid.id}, description: ${droid.description}"
}
