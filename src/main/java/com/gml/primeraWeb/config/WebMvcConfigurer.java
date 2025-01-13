package com.gml.primeraWeb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.addViewControllers(registry);

        registry.addViewController("/403").setViewName("403");
    }
}
