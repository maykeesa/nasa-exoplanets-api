package br.com.nasa.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nasa.model.Planet;
import br.com.nasa.model.dto.EarthCompDto;
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
	private final double DENS_TERRA = 5.514;
	
	@Autowired private PlanetRepository planetRep;
	@Autowired private GptService gptService;
	
	public PlanetDto createPlanet(PlanetForm form) throws JsonProcessingException {
		String naturePrompt = this.calcNature(form.getNatureza());
		String surfacePrompt = this.calcSurface(form.getSuperficie());
		double massaTerra = MASSA_TERRA * form.getMassa();
		double densTerra = DENS_TERRA * form.getDensidade();
		double raioTerra = RAIO_TERRA * form.getRaio();
		
		Planet planet = new Planet(form.getNomePlaneta(), 
				form.getAgua(), raioTerra, massaTerra, densTerra, 
				form.getPeriodoOrbita(), naturePrompt, surfacePrompt, 
				form.getTemperatura());
		
		this.savePlanet(planet);
		EarthCompDto comparatorDto = this.createCompEarth(planet);
		GptResponse commandGpt = this.gptService.commandGpt(this.createPrompt(planet.getId()));
		
		return new PlanetDto(planet, commandGpt, comparatorDto);
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
	
	private EarthCompDto createCompEarth(Planet planet) {
		String compAgua = this.comparatorAgua(planet);
		String compRaio = this.comparatorRaio(planet);
		String compMassa = this.comparatorMassa(planet);
		String compDensidade = this.comparatorDensidade(planet);
		String compOrbita = this.comparatorPeriodoOrbita(planet);
		String compTemp = this.comparatorTemperatura(planet);

		return new EarthCompDto(compAgua, compRaio, compMassa, compDensidade, compOrbita, compTemp);
	}
	
	private String comparatorAgua(Planet planet) {
		return "Did you know that on planet Earth 70% of its surface area is covered by water? "
				+ "In "+ planet.getNome() +" it is " + planet.getAgua() + "%, but this does not mean that "
				+ "it has the same amount as the earth, since the radius of "+ planet.getNome() +" can be "
				+ "different from that of the earth.";
	}
	
	private String comparatorRaio(Planet planet) {
		if(planet.getRaio() >= RAIO_TERRA) {
			double vezesMaior = planet.getRaio()/RAIO_TERRA;
			
			return "The "+ planet.getNome() +" has a radius of "+ planet.getRaio() +"Km, while the "
				+ "Earth has a radius of "+ RAIO_TERRA +"km, we can say that the "+ planet.getNome()
				+ " is " + vezesMaior + " times larger than the Earth, that is, " + planet.getNome()
				+ " is equivalent to " + vezesMaior + " Earths.";
		}
		
		double vezesMenor = planet.getRaio()/RAIO_TERRA;
		
		return "The "+ planet.getNome() +" has a radius of "+ planet.getRaio() +"Km, while the "
			+ "Earth has a radius of "+ RAIO_TERRA +"km, we can say that the "+ planet.getNome()
			+ " is " + (vezesMenor * 100) + " the value of the Earth.";
	}
	
	private String comparatorMassa(Planet planet) {
		if(planet.getMassa() >= MASSA_TERRA) {
			double vezesMaior = planet.getMassa()/MASSA_TERRA;
			
			return  planet.getNome() + " mass is "+ planet.getMassa() +" * 10^24 Kg, while "
				+ "Earth's is "+ MASSA_TERRA +" * 10^24 Kg, this makes "+ planet.getNome() + " " + vezesMaior + " times "
				+ "more massive than Earth.";
		}
		
		double vezesMenor = planet.getMassa()/MASSA_TERRA;
		
		return planet.getNome() + " mass is "+ planet.getMassa() +" * 10^24 Kg, while Earth's "
			+ "is "+ MASSA_TERRA +" * 10^24 Kg, this makes "+ planet.getNome() + " only "
			+ (vezesMenor * 100) + "% of Earth's mass.";
	}
	
	private String comparatorDensidade(Planet planet) {
		return "The Earth's density is "+ DENS_TERRA+ "g/cm^3, while "+ planet.getNome()+" is"
				+ " "+ planet.getDensidade() +"g/cm^3.";
	}
	
	private String comparatorPeriodoOrbita(Planet planet) {
		return "While on Earth the year lasts "+ ORBITA_TERRA +" days, on "+ planet.getNome()+" the "
				+ "year lasts "+ planet.getPeriodoOrbita() +" days.";
	}
	
	private String comparatorTemperatura(Planet planet) {
		if(planet.getTemperatura() < TEMP_TERRA) {
			double diferenca = TEMP_TERRA - planet.getTemperatura();
			
			return "While on Earth the planet's average temperature is "+ TEMP_TERRA +"°C, on "
				+ planet.getNome() + " the average temperature is "+ planet.getTemperatura()+"°C, "
				+ diferenca + "°C less than Earth.";
		}
		
		double diferenca = planet.getTemperatura() - TEMP_TERRA;
		
		return "While on Earth the planet's average temperature is "+ TEMP_TERRA +"°C, on "
			+ planet.getNome() + " the average temperature is "+ planet.getTemperatura()+"°C, "
			+ diferenca + "°C more than Earth.";
	}
}
