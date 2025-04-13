package com.jagt.mangareader.manga.infrastructure.input.rest.response.feed;

public record FeedAttributesDTO(
        String volume,
        String chapter,
        String title,
        String language,
        int pages
) {
}
