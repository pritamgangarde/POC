package com.docker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docker.entity.Plants;

@Repository
public interface PlantsRepository extends CrudRepository<Plants, Integer> {

}
