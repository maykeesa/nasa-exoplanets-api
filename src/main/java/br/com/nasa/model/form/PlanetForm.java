package br.com.nasa.model.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class PlanetForm {

	private String nomePlaneta;
	@Min(value = (long) 0.3) @Max(value = (long) 0.8)
	private double agua;
	@Min(value = (long) 0.8) @Max(value = (long) 2.5)
	private double raio;
	@Min(value = (long) 0.3) @Max(value = (long) 7.6)
	private double massa;
	@Min(value = (long) 2.4) @Max(value = (long) 7.8)
	private double densidade;
	@Min(value = (long) 0) @Max(value = (long) 1)
	private double natureza;
	@Min(value = (long) 0) @Max(value = (long) 1)
	private double superficie;
	@Min(value = (long) 4) @Max(value = (long) 636)
	private int periodoOrbita;
	@Min(value = (long) 9) @Max(value = (long) 25)
	private double temperatura; 
	
	public PlanetForm() {
		
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nomePlaneta + "| Natureza: " + this.natureza; 
	}
}
