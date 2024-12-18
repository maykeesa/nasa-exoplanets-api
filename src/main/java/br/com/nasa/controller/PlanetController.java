package br.com.nasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nasa.model.Planet;
import br.com.nasa.model.dto.PlanetDto;
import br.com.nasa.model.form.PlanetForm;
import br.com.nasa.service.PlanetService;

@RestController
@RequestMapping("/planet")
public class PlanetController {

	@Autowired private PlanetService planetService;
	 
	@GetMapping
	public ResponseEntity<List<Planet>> getAll(){
		List<Planet> all = this.planetService.getAll();
		if(all != null) {
			return ResponseEntity.ok(all);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<PlanetDto> createPlanet(@RequestBody PlanetForm form) throws JsonProcessingException{
		PlanetDto createPlanet = this.planetService.createPlanet(form);
		return ResponseEntity.ok(createPlanet);
	}
}
