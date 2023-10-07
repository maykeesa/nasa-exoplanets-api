package br.com.nasa.model.response.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UsageResponse {

	@JsonProperty(value = "prompt_tokens")
	private int promptTokens;
	@JsonProperty(value = "completion_tokens")
	private int completionTokens;
	@JsonProperty(value = "total_tokens")
	private int totalTokens;
    
    public UsageResponse() {
    	
    }
}
