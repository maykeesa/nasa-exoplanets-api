package br.com.nasa.model.dto;

import java.util.UUID;

import br.com.nasa.model.Planet;
import br.com.nasa.model.response.gpt.GptResponse;
import lombok.Data;

@Data
public class PlanetDto {

	private UUID id;
	private String nome;
	private String agua;
	private String raio;
	private String massa;
	private String densidade;
	private String periodoOrbita;
	private String temperatura;
	private GptDto gpt;
	private EarthCompDto earthComparator;
	
	public PlanetDto(Planet planet, GptResponse resp, EarthCompDto earthCompDto) {
		this.id = planet.getId();
		this.nome = planet.getNome();
		this.agua = planet.getAgua() + "%";
		this.raio = planet.getRaio() + "Km";
		this.massa = planet.getMassa() + " * 10^24 Kg";
		this.densidade = planet.getDensidade() + "g/cm^3";
		this.periodoOrbita = planet.getPeriodoOrbita() + " days";
		this.temperatura = planet.getTemperatura() + "°C";
		this.gpt = new GptDto(resp);
		this.earthComparator = earthCompDto;
	}
}
