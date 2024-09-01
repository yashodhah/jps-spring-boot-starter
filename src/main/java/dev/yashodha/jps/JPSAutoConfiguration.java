package dev.yashodha.jps;

import dev.yashodha.jps.posts.JPSPostClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@EnableConfigurationProperties(JPSProperties.class)
public class JPSAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(JPSAutoConfiguration.class);
    private final JPSProperties jpsProperties;

    public JPSAutoConfiguration(JPSProperties jpsProperties) {
        log.debug("Constructing JPS with properties {}", jpsProperties);
        this.jpsProperties = jpsProperties;
    }

    @Bean("JPSRestClient")
    RestClient restClient(RestClient.Builder builder){
        return builder
                .baseUrl(jpsProperties.baseURL())
                .build();
    }

    @Bean
    JPSPostClient jpsPostClient(RestClient restClient){
        return new JPSPostClient(restClient);
    }
}
