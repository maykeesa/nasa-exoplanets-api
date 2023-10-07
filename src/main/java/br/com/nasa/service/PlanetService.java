package br.com.nasa.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nasa.model.Planet;
import br.com.nasa.model.dto.PlanetDto;
import br.com.nasa.model.form.PlanetForm;
import br.com.nasa.model.response.gpt.GptResponse;
import br.com.nasa.repository.PlanetRepository;

@Service
public class PlanetService {

	private final double TEMP_TERRA = 17.18;
	private final double RAIO_TERRA = 6.371;
	private final double ORBITA_TERRA = 365;
	private final double MASSA_TERRA = 5.9742;
	private final double AGUA_TERRA = 1358099876.0;
	private final double DENS_TERRA = 5.514;
	
	@Autowired
	private PlanetRepository planetRep;
	@Autowired
	private GptService gptService;
	
	private PlanetDto createPlanet(PlanetForm form) throws JsonProcessingException {
		String naturePrompt = this.calcNature(form.getNatureza());
		String surfacePrompt = this.calcSurface(form.getSuperficie());
		
		Planet planet = new Planet(form.getId(), form.getNomePlaneta(), 
				form.getAgua(),form.getRaio(), form.getMassa(), form.getDensidade(), 
				form.getPeriodoOrbita(), naturePrompt, surfacePrompt, 
				form.getTemperatura());
		
		this.savePlanet(planet);
		GptResponse commandGpt = this.gptService.commandGpt(this.createPrompt(planet.getId()));
		
		return new PlanetDto(planet);
	}
	
	private void savePlanet(Planet planet) {
		this.planetRep.saveAndFlush(planet);
	}
	
	private String calcNature(double nature) {
		if(nature >= 0 && nature <=0.25) {
			return "nature will be almost non-existent, few species of animals and plants";
		}else if(nature >= 0.26 && nature <= 0.50) {
			return "nature will be abundant, but not as much as the number of species on earth";  
		}else if(nature >= 0.51 && nature <= 0.75) {
			return "nature will be abundant, greater than the number of species that the earth"; 
		}else {
			return "nature will be very abundant, many times greater than that of the earth";
		}
	}
	
	private String calcSurface(double Surface) {
		if(Surface >= 0 && Surface <=0.25) {
			return "of plains";
		}else if(Surface >= 0.26 && Surface <= 0.50) {
			return "mountains, but the vast majority is flat";  
		}else if(Surface >= 0.51 && Surface <= 0.75) {
			return "mountains and plains, well balanced"; 
		}else {
			return "is just mountains, plains are almost non-existent";
		}
	}
	
	private String createPrompt(UUID id) {
		Planet planet = this.planetRep.findById(id).get();
		
		String text = "Describe in a detailed form a habitable exoplanet named " + planet.getNome() + " where "
		+ "the exoplanet is compound of (‘"+ planet.getAgua() +"’+%) water, the fauna and flora of this planet "
		+ "have a "+ planet.getNatureza() +" diversity of life and the terrain of it is predominantly "
		+ "composed of "+ planet.getSuperficie() +". The exoplanet has an orbit "
		+ "period of "+ planet.getPeriodoOrbita() +" days and has an average surface "
		+ "temperature of "+ planet.getTemperatura() +"."
		+ "\n\n"
		+ "- If you want you can use the datas of the orbit period to generate the distance of the "
		+ "exoplanet to the star of its solar system based on this formula T^2 = (4π^2 * a^3) / (G * M)."
		+ "\n"
		+ "- Do that description very well detailed please, giving good information about fauna and flora."
		+ "\n"
		+ "- That description should be in a single text not containing unordered lists. Or any kind "
		+ "of list"
		+ "\n"
		+ "- Do that description in a single text summary containing 200 words"
		+ "\n"
		+ "- And please dont show the formula on the generated text"
		+ "\n"
		+ "- When you are generating the names of the fauna animals and flora plants try not to "
		+ "generate a too many fictional name";
		
		return text;
	}
}
