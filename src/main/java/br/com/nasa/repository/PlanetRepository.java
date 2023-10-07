package br.com.nasa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nasa.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, UUID>{

}
