package com.docker.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.docker.dto.PlantsDTO;
import com.docker.entity.Plants;

public class AppUtils {
	public static PlantsDTO entityToDTO(Plants plants) {
		PlantsDTO plantsDTO = new PlantsDTO();
		BeanUtils.copyProperties(plants, plantsDTO);
		return plantsDTO;
	}

	public static Plants dtoToEnttity(PlantsDTO plantsDTO) {
		Plants plants = new Plants();
		BeanUtils.copyProperties(plantsDTO, plants);
		return plants;
	}

	public static List<PlantsDTO> entityToDTO(List<Plants> listPlants) {
		List<PlantsDTO> listPlantsDTO = new ArrayList<PlantsDTO>();
		listPlants.forEach(plant -> {
			PlantsDTO plantDTO = new PlantsDTO();
			BeanUtils.copyProperties(plant, plantDTO);
			listPlantsDTO.add(plantDTO);
		});
		return listPlantsDTO;
	}

	public static List<Plants> dtoToEntity(List<PlantsDTO> listPlantsDTO) {
		List<Plants> listPlants = new ArrayList<Plants>();
		listPlantsDTO.forEach(plantsDTO -> {
			Plants plants = new Plants();
			BeanUtils.copyProperties(plantsDTO, plants);
			listPlants.add(plants);
		});
		return listPlants;
	}
}