package br.com.nasa.config.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class DocConfiguration {

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Nasa-App")
						.description("Create your Explanet.")
						.version("1.0"));
	}
}
