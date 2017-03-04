package com.brinz.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  @Bean
  public String applicationVersion() {
    System.out.println("app version: " + this.getClass().getPackage().getImplementationVersion());
    return this.getClass().getPackage().getImplementationVersion();
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}