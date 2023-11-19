package com.javatechie.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.dto.PlantsDTO;
import com.javatechie.entity.Plants;
import com.javatechie.repository.PlantsRepository;
import com.javatechie.utils.AppUtils;

@Service
public class PlantsService {

	@Autowired
	private PlantsRepository plantsRepository;

	public List<PlantsDTO> getAllPlants() {
		List<Plants> listPlants = StreamSupport.stream(plantsRepository.findAll().spliterator(), false).toList();
		return AppUtils.entityToDTO(listPlants);
	}

	public PlantsDTO findById(Integer id) {
		PlantsDTO plantsDTO = null;
		Optional<Plants> optionalPlants = plantsRepository.findById(id);
		if (optionalPlants.isPresent()) {
			plantsDTO = new PlantsDTO();
			plantsDTO = AppUtils.entityToDTO(optionalPlants.get());
		}
		return plantsDTO;
	}

	public PlantsDTO savePlantDetails(PlantsDTO plantsDTO) {
		Plants plants = new Plants();
		BeanUtils.copyProperties(plantsDTO, plants);
		plants.setInsertedDate(new Date());
		plants.setUpdatedDate(new Date());
		return AppUtils.entityToDTO(plantsRepository.save(plants));
	}

	public PlantsDTO updatePlantDetails(PlantsDTO plantsDTO, Integer id) {
		Plants plants = AppUtils.dtoToEnttity(plantsDTO);
		plants.setPlantId(id);
		plants.setInsertedDate(new Date());
		plants.setUpdatedDate(new Date());
		return AppUtils.entityToDTO(plantsRepository.save(plants));
	}

	public void deletePlantDetails(Integer id) {
		plantsRepository.deleteById(id);
	}

}
