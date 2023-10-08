package br.com.nasa.model.form;

import lombok.Getter;

@Getter
public class IAForm {

	private String prompt;
	
	public IAForm() {
	}
	
	public IAForm(String prompt) {
		this.prompt = prompt;
	}
	
}
