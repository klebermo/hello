package org.hello;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.hello.custom.thymeleaf.dialect.FormDialect;
import org.hello.custom.thymeleaf.dialect.NavDialect;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

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
	
	public static List<Class<?>> getListaClasses(String target) throws ClassNotFoundException {
		List<Class<?>> lista = new ArrayList<Class<?>>();
		
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		Class<? extends Annotation> annotation = (Class<? extends Annotation>) Class.forName("org.hello.custom.annotation.nav." + target);
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotation));
		for (BeanDefinition bd : scanner.findCandidateComponents("com.spring.loja.model")) {
			Class<?> clazz = Class.forName(bd.getBeanClassName());
			lista.add(clazz);
		}
		
		return lista;
	}
	
}
