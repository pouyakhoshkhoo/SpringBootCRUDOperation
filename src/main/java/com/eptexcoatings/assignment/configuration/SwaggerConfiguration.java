package com.eptexcoatings.assignment.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI warehouseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Assignment Application")
                        .description("Assignment Application Api Documentation")
                        .license(new License().name("EPTEX Coatings LLC.").url("https://www.eptexcoatings.com/")));
    }
}
