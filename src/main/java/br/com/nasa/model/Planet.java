package br.com.nasa.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	private double agua;
	private double raio;
	private double massa;
	private double densidade;
	private int periodoOrbita;
	private String natureza;
	private String superficie;
	private double temperatura;
	private String responsePlanet;
	
	public Planet() {
		
	}
	
	public Planet(UUID id, String nomePlaneta, double agua, double raio, double massa, 
			double densidade, int periodoOrbita, String natureza, 
			String superficie, double temperatura) {
		
		this.id = id;
		this.nome = nomePlaneta;
		this.agua = (agua * 100);
		this.raio = raio;
		this.massa = massa;
		this.densidade = densidade;
		this.periodoOrbita = periodoOrbita;
		this.natureza = natureza;
		this.superficie = superficie;
		this.temperatura = temperatura;
	}
	
}
