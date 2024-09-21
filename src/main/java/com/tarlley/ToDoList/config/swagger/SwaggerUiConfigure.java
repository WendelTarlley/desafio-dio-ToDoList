package com.tarlley.ToDoList.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUiConfigure {


    @Bean
    public GroupedOpenApi springShopOpenAPI() {
        return GroupedOpenApi.builder()
                .group("Auth-api")
                .packagesToScan("com.tarlley.ToDoList.controller")
                .build();
    }

}
