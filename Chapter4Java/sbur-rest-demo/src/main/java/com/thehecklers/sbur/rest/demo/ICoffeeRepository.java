package com.thehecklers.sbur.rest.demo;

import org.springframework.data.repository.CrudRepository;

public interface ICoffeeRepository extends CrudRepository<Coffee, String> {
}
