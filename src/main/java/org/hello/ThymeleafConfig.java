package org.hello;

import java.util.HashSet;
import java.util.Set;

import org.hello.custom.thymeleaf.dialect.FormDialect;
import org.hello.custom.thymeleaf.dialect.NavDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Configuration
public class ThymeleafConfig {
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		
		final Set<IDialect> dialects = new HashSet<IDialect>();
		dialects.add(new FormDialect());
		dialects.add(new NavDialect());
		engine.setDialects( dialects );
		
		return engine;
	}

}
