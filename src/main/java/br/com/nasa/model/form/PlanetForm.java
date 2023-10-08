package br.com.nasa.model.form;

import lombok.Getter;

@Getter
public class PlanetForm {

	private String id;
	private String nome;
	//Min 0.3 e Max 0.8
	private double agua;
	//Min 0.8 e Max 2.5
	private double raio;
	//Min 0.3 e Max 7.6
	private double massa;
	//Min 2.4 e Max 7.8
	private double densidade;
	//Min 0 e Max 100
	private double natureza;
	//Min 0 e Max 100
	private double superficie;
	//Min 4 e Max 636
	private int periodoOrbita;
	//Min 9 e Max 25
	private double temperatura; 
	
	public PlanetForm() {
		
	}
}
