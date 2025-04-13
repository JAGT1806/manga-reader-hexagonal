package com.jagt.mangareader.manga.infrastructure.output.client.dto.feed;

public record FeedAttributesClientResponse(
        String volume,
        String chapter,
        String title,
        String translatedLanguage,
        int pages
) {
}
