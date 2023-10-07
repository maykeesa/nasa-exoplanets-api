package br.com.nasa.model.response.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class MessageResponse {
	
	@JsonProperty(value = "role")
	private String role;
	@JsonProperty(value = "content")
	private String content;
    
    public MessageResponse() {
	}
}
