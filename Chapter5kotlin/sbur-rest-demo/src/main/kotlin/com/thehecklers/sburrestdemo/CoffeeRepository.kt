package com.thehecklers.sburrestdemo

import org.springframework.data.repository.CrudRepository

interface CoffeeRepository : CrudRepository<Coffee, String>
