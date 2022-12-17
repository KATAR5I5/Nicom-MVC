package ru.markelov.security.FirstSecurityApp.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/styles/css/**")
                .addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("classpath:/static/images/");
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("classpath:/static/js/");
    }
}
