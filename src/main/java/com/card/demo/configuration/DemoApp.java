package com.card.demo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(AppConfig.class)
public class DemoApp extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApp.class);
    }
    
    public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApp.class, args);
	}
    
    
    
}
