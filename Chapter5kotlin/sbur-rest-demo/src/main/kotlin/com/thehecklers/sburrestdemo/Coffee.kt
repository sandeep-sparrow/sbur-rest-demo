package com.thehecklers.sburrestdemo

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Coffee(val name: String = "Cup O' Joe", @Id val id: String = UUID.randomUUID().toString())
