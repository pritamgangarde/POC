package com.javatechie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.dto.PlantsDTO;
import com.javatechie.repository.PlantsRepository;
import com.javatechie.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlantsService {

	@Autowired
	private PlantsRepository plantsRepository;

	public Flux<PlantsDTO> getAllPlants() {
		return plantsRepository.findAll().map(AppUtils::entityToDTO);
	}

	public Mono<PlantsDTO> findById(Integer id) {
		return plantsRepository.findById(id).map(AppUtils::entityToDTO);
	}

	public Mono<PlantsDTO> savePlantDetails(Mono<PlantsDTO> monoPlantsDTO) {
		return monoPlantsDTO.map(AppUtils::dtoToEnttity).flatMap(plantsRepository::save).map(AppUtils::entityToDTO);
	}

	public Mono<PlantsDTO> updatePlantDetails(Mono<PlantsDTO> monoPlantsDTO, Integer id) {
		return plantsRepository.findById(id).flatMap(plant -> monoPlantsDTO.map(AppUtils::dtoToEnttity)
				.doOnNext(plants -> plants.setPlantId(id)).flatMap(plantsRepository::save).map(AppUtils::entityToDTO));
	}

	public Mono<Void> deletePlantDetails(Integer id) {
		return plantsRepository.deleteById(id);
	}

}
