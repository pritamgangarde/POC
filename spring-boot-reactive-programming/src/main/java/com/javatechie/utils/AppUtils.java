package com.javatechie.utils;

import org.springframework.beans.BeanUtils;

import com.javatechie.dto.PlantsDTO;
import com.javatechie.entity.Plants;

public class AppUtils {
	public static PlantsDTO entityToDTO(Plants plants) {
		PlantsDTO plantsDTO=new PlantsDTO();
		BeanUtils.copyProperties(plants, plantsDTO);
		return plantsDTO;
	}
	
	public static Plants dtoToEnttity(PlantsDTO plantsDTO) {
		Plants plants=new Plants();
		BeanUtils.copyProperties(plantsDTO, plants);
		return plants;
	}
}