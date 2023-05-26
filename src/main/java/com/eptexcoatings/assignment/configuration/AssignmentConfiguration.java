package com.eptexcoatings.assignment.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Order(1)
@Configuration
public class AssignmentConfiguration {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }
}
