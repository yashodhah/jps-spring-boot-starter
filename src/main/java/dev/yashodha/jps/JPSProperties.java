package dev.yashodha.jps;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("json-placeholder-service")
public record JPSProperties(
        @DefaultValue("https://jsonplaceholder.typicode.com")
        String baseURL
) {
}
