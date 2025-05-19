package net.youtics.backend.crudProductos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Aplica para todos los endpoints
                        .allowedOrigins("https://erp-front-yccp.onrender.com")  // URL de tu frontend desplegado
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                        .allowedHeaders("*");  // Permite cualquier header
            }
        };
    }
}