package dev.yashodha.jps.posts;

import dev.yashodha.jps.JPSAutoConfiguration;
import dev.yashodha.jps.JPSClient;
import dev.yashodha.jps.JPSProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JPSPostClientTest {
    private final ApplicationContextRunner contextRunner =  new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JPSAutoConfiguration.class, RestClientAutoConfiguration.class));

    @Test
    void shouldContainTodoRestClientBean() {
        contextRunner.run(context -> {
            assertTrue(context.containsBean("JPSRestClient"));
            assertTrue(context.containsBean("jpsPostClient"));
        });
    }


    @Test
    void shouldContainDefaultBaseUrl() {
        contextRunner
                .run((context) -> {
                    assertThat(context).hasSingleBean(JPSProperties.class);
                    assertThat(context.getBean(JPSProperties.class).baseURL()).isEqualTo("https://jsonplaceholder.typicode.com");
                });
    }

    @Test
    void shouldSetCustomBaseUrl() {
        contextRunner
                .withPropertyValues("json-placeholder-service.baseURL=https://localhost:3000")
                .run((context) -> {
                    assertThat(context).hasSingleBean(JPSProperties.class);
                    assertThat(context.getBean(JPSProperties.class).baseURL()).isEqualTo("https://localhost:3000");
                });
    }

    @Test
    void shouldFindAllPosts() {
        contextRunner
                .run((context) -> {
                    JPSPostClient postClient = context.getBean(JPSPostClient.class);
                    assertEquals(100, postClient.findAll().size());
                });
    }
}
