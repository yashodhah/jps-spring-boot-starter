package dev.yashodha.jps.posts;

public record Post(
        String id,
        String userId,
        String title,
        String body
) {
}
