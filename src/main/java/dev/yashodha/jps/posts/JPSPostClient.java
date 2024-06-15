package dev.yashodha.jps.posts;

import dev.yashodha.jps.JPSClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

public class JPSPostClient implements JPSClient<Post> {

    private final RestClient restClient;

    public JPSPostClient(@Qualifier("JPSRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public Post findById(Integer id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public Post create(Post post) {
        return restClient.post()
                .uri("/todos")
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public Post update(Integer id, Post post) {
        return restClient.put()
                .uri("/todos/{id}", id)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return restClient.delete()
                .uri("/todos/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
