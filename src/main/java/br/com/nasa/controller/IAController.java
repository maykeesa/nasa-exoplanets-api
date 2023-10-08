package br.com.nasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.nasa.model.dto.BingDto;
import br.com.nasa.model.dto.GptDto;
import br.com.nasa.model.form.IAForm;
import br.com.nasa.model.response.gpt.GptResponse;
import br.com.nasa.service.BingService;
import br.com.nasa.service.GptService;

@RestController
@RequestMapping("/ia")
public class IAController {

	@Autowired private GptService gptService;
	@Autowired private BingService bingService;
	
	@PostMapping
	public ResponseEntity<GptDto> gptQuestion(@RequestBody IAForm form) throws JsonProcessingException{
		GptResponse resp = this.gptService.commandGpt(form.getPrompt());
		
		return ResponseEntity.ok(new GptDto(resp));
	}
	
	@PostMapping
	public ResponseEntity<BingDto> bingQuestion(@RequestBody IAForm form) throws JsonProcessingException{
		List<String> imagens = this.bingService.commandBing(form.getPrompt());
		
		return ResponseEntity.ok(new BingDto(imagens));
	}
}
