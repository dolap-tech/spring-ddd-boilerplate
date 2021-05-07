package com.dolap.product.base.integration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public abstract class BaseIntegrationTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    final static private PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Startables.deepStart(Stream.of(POSTGRES_CONTAINER)).join();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MapPropertySource propertySource = new MapPropertySource("postgres", Collections.unmodifiableMap(createConnectionConfiguration()));
        environment.getPropertySources().addFirst(propertySource);
    }

    private Map<String, String> createConnectionConfiguration() {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl());
        stringStringMap.put("spring.datasource.username", POSTGRES_CONTAINER.getUsername());
        stringStringMap.put("spring.datasource.password", POSTGRES_CONTAINER.getPassword());
        return stringStringMap;
    }
}

