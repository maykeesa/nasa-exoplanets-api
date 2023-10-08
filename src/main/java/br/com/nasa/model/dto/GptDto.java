package br.com.nasa.model.dto;

import br.com.nasa.model.response.gpt.GptResponse;
import lombok.Getter;

@Getter
public class GptDto {

	private String model;
	private String content;
	
	public GptDto(GptResponse resp) {
		this.model = resp.getModel();
		this.content = resp.getChoices().get(0).getMessage().getContent();
	}
}
