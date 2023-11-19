package com.javatechie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.entity.Plants;

@Repository
public interface PlantsRepository extends CrudRepository<Plants, Integer> {

}
