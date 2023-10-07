package br.com.nasa.model.response.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class ChoiceResponse {
	
	@JsonProperty(value = "index")
	private int index;
    @JsonProperty(value = "message")
    private MessageResponse message;
    @JsonProperty(value = "finish_reason")
    private String finishReason;
    
    public ChoiceResponse() {
	}
}
