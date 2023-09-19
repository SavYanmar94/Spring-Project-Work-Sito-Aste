package it.corso.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// localhost:8080/swagger-ui.html
@Configuration
public class DocumentConfig {

	@Bean //senza Bean per Springboot è un metodo di servizio 
	public OpenAPI getDocumentAPI() {
		OpenAPI api = new OpenAPI();
		Info info = new Info();
		info.title("Library Web Service");
		info.description("RESTful Web Service for Library Project");
		//i webservice sono creati con architettura o REST ( protocollo http ) o SOAP 
		info.version("1.0.0");
		api.info(info);
		return api;
		
		//oggetti dell'ioc container sono Bean
		
	}
	//bean : dichiarazione e istanzione oggetto
}
