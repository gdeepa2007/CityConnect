package com.cities.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("!prod")
public class SwaggerConfig {
	
	@Bean
	public Docket swaggerConfiguration () {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cities"))
				.build();
	}
	
	private ApiInfo apiDetails () {
		return new ApiInfoBuilder()
				.title("City Connect API documentation")
				.description("Documentation of City Connect Application APIs")
				.contact(new Contact("Deepa George", null, "gdeepa2007@gmail.com"))
				.version("1.0")
				.build();
	}
	
}
