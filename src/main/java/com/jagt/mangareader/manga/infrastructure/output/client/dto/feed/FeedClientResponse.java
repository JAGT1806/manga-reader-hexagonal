package com.jagt.mangareader.manga.infrastructure.output.client.dto.feed;

public record FeedClientResponse(
        String id,
        FeedAttributesClientResponse attributes
) {
}
