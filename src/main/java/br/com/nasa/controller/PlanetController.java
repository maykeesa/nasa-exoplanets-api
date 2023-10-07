package br.com.nasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nasa.model.form.PlanetForm;
import br.com.nasa.service.PlanetService;

@RestController
@RequestMapping("/planet")
public class PlanetController {

	@Autowired
	private PlanetService planetService;
	 
	@PostMapping
	public void createPlanet(@RequestBody PlanetForm form){
		System.out.println(form.toString());
	}
}
