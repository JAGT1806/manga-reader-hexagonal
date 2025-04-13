package com.jagt.mangareader.manga.infrastructure.output.client.dto.chapter;

public record ChapterClientResponse(
        String baseUrl,
        ChapterAttributesClientResponse chapter
) {
}
