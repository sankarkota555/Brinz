package com.brinz.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.brinz")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver getInternalViewResolver() {
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    internalResourceViewResolver.setOrder(1);
    internalResourceViewResolver.setPrefix("WEB-INF/views");
    internalResourceViewResolver.setSuffix(".html");
    return internalResourceViewResolver;
  }
  
  
 /* @Bean
  public ViewResolver getResourceBundleView(){
    ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
    resourceBundleViewResolver.setBasename("views");
    resourceBundleViewResolver.setOrder(2);    
    return resourceBundleViewResolver;
  }*/

}
