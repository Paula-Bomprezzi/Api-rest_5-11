package com.utn.productos_api.Configuraciones;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Productos")
                        .version("1.0.0")
                        .description("API REST para gestión de productos")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@example.com")));
    }
}

