package com.example.spring.jwt.mongodb.config;

//import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class UserApiConfig {
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("all")
//                .pathsToMatch("/api/v1/auth/**","/file/**","/mail/**","/threadmail/**")
//                .build();
//    }
    
    @Bean
    public OpenAPI springShopOpenAPI() {
          final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("Spring Application with Security  APIs")
                .description("Thread Email and File operation sample application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("Mail Documentation")
                .url("https://github.com/shitalpbhinge/SpringApplicationWithSecurity"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                      .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                          .name(securitySchemeName)
                          .type(SecurityScheme.Type.HTTP)
                          .scheme("bearer")
                          .bearerFormat("JWT")));
    }
}