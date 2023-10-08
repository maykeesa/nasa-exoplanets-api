package br.com.nasa.model.dto;

import lombok.Getter;

@Getter
public class EarthDto {

	private double periodoOrbita;
	private double densidade;
	private double raio;
	private double massa;
	private double temperatura;
	
	public EarthDto(double periodoOrbita, double densidade, double raio, double massa, double temperatura) {
		this.periodoOrbita = periodoOrbita;
		this.densidade = densidade;
		this.raio = raio;
		this.massa = massa;
		this.temperatura = temperatura;
	}
}
