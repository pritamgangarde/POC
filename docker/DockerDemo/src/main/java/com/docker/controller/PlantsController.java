package com.docker.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.dto.PlantsDTO;
import com.docker.service.PlantsService;

@RestController
@RequestMapping("/plants")
public class PlantsController {

	@Autowired
	private PlantsService plantsService;

	@GetMapping
	public List<PlantsDTO> getAllPlants() {
		return plantsService.getAllPlants();
	}

	@GetMapping("/{plantId}")
	public PlantsDTO getProductsById(@PathVariable("plantId") Integer id) {
		return plantsService.findById(id);
	}

	@PostMapping
	public PlantsDTO savePlantDetails(@RequestBody PlantsDTO monoPlantDTO) {
		return plantsService.savePlantDetails(monoPlantDTO);
	}

	@PutMapping("/update/{id}")
	public PlantsDTO updatePlantDetails(@RequestBody PlantsDTO monoPlantDTO, @PathVariable Integer id) {
		return plantsService.updatePlantDetails(monoPlantDTO, id);
	}

	@DeleteMapping("/delete/{plantId}")
	public String deletePlantDetails(@PathVariable("plantId") Integer id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			plantsService.deletePlantDetails(id);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			throw new Exception("Getting error while deleting the plant details");
		}
		return jsonObject.toString();
	}
}
