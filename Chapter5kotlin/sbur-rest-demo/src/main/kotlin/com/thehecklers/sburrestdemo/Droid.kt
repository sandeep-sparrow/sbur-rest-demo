package com.thehecklers.sburrestdemo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "droid")
@ConstructorBinding
class Droid(val id: String, val description: String) {
}
