package com.javatechie.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.entity.Plants;

@Repository
public interface PlantsRepository extends ReactiveCrudRepository<Plants, Integer> {

}
