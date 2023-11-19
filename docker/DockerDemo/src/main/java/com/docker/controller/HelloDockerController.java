package com.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker")
public class HelloDockerController {
	@GetMapping
	public String helloDocker() {
		return "Hello Docker!";
	}
}
