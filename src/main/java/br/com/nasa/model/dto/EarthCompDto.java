package br.com.nasa.model.dto;

import lombok.Getter;

@Getter
public class EarthCompDto {

	private String comparatorAgua;
	private String comparatorRaio;
	private String comparatorMassa;
	private String comparatorDensidade;
	private String comparatorOrbita;
	private String comparatorTemp;
	
	public EarthCompDto(String agua, String raio, String massa, String densidade, 
			String orbita, String temperatura) {
		this.comparatorAgua = agua;
		this.comparatorRaio = raio;
		this.comparatorMassa = massa;
		this.comparatorDensidade = densidade;
		this.comparatorOrbita = orbita;
		this.comparatorTemp =temperatura;
	}
}
