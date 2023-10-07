package br.com.nasa.model.form;

import lombok.Getter;

@Getter
public class GptForm {

	private String prompt;
	
	public GptForm() {
	}
	
	public GptForm(String prompt) {
		this.prompt = prompt;
	}
	
}
