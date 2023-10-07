package br.com.nasa.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nasa.model.response.gpt.GptResponse;
import lombok.Data;

@Data
@Service
public class GptService {

	@Value("${gpt.key}")
	private String key;
	@Value("${gpt.chat.link}")
	private String urlApi;

	public GptResponse commandGpt(String prompt) throws JsonProcessingException {
		List<HashMap<String, String>> hmPrompt = this.hashMapCommand(prompt);
		HashMap<String, Object> hmData = this.hashMapData(hmPrompt);
		
	    ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(hmData);
		
		RestTemplate rest = new RestTemplateBuilder()
				.defaultHeader("Content-Type", "application/json")
				.defaultHeader("Authorization", "Bearer " + this.key)
				.build();
		
		ResponseEntity<GptResponse> postForEntity = rest.postForEntity(this.urlApi, json, GptResponse.class);
		
		return postForEntity.getBody();
	}
	
	@SuppressWarnings("serial")
	public HashMap<String, Object> hashMapData(List<HashMap<String, String>> hashMapCommand){
		HashMap<String, Object> hm = new HashMap<>(){{
			put("model", "gpt-3.5-turbo");
			put("messages", hashMapCommand);
			
		}};
		
		return hm;
	}
	
	@SuppressWarnings("serial")
	public List<HashMap<String, String>> hashMapCommand(String prompt){
		HashMap<String, String> hm = new HashMap<>(){{
			put("role", "user");
			put("content", prompt);
			
		}};
		
		return Arrays.asList(hm);
	}
	
}
