package com.javatechie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.dto.PlantsDTO;
import com.javatechie.service.PlantsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/plants")
public class PlantsController {

	@Autowired
	private PlantsService plantsService;

	@GetMapping
	public Flux<PlantsDTO> getAllPlants() {
		return plantsService.getAllPlants();
	}

	@GetMapping("/{plantId}")
	public Mono<PlantsDTO> getProductsById(@PathVariable("plantId") Integer id) {
		return plantsService.findById(id);
	}

	@PostMapping
	public Mono<PlantsDTO> savePlantDetails(@RequestBody Mono<PlantsDTO> monoPlantDTO) {
		return plantsService.savePlantDetails(monoPlantDTO);
	}

	@PutMapping("/update/{id}")
	public Mono<PlantsDTO> updatePlantDetails(@RequestBody Mono<PlantsDTO> monoPlantDTO, @PathVariable Integer id) {
		return plantsService.updatePlantDetails(monoPlantDTO, id);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<Void> deletePlantDetails(Integer id) {
		return plantsService.deletePlantDetails(id);
	}
}
