package org.hello;

import org.hello.custom.thymeleaf.dialect.FormDialect;
import org.hello.custom.thymeleaf.dialect.NavDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	@Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
	
	@Bean
	public FormDialect formDialect() {
		return new FormDialect();
	}
	
	@Bean
	public NavDialect navDialect() {
		return new NavDialect();
	}
	
}
