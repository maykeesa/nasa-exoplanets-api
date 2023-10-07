package br.com.nasa.model.response.gpt;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GptResponse {

	@JsonProperty(value = "id")
    private String id;
	@JsonProperty(value = "object")
	private String object;
	@JsonProperty(value = "created")
	private int created;
	@JsonProperty(value = "model")
	private String model;
	@JsonProperty(value = "choices")
	private ArrayList<ChoiceResponse> choices;
	@JsonProperty(value = "usage")
	private UsageResponse usage;
    
    public GptResponse() {
    	
    }
}
