package br.com.nasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nasa.model.form.GptForm;
import br.com.nasa.model.response.gpt.GptResponse;
import br.com.nasa.service.GptService;

@RestController
@RequestMapping("/gpt")
public class GptController {

	@Autowired
	private GptService gptService;
	
	@PostMapping
	public void gptQuestion(@RequestBody GptForm form) throws JsonProcessingException{
		GptResponse resp = this.gptService.commandGpt(form.getPrompt());
		System.out.println(resp.getChoices().get(0).getMessage().getContent());
	}
}
