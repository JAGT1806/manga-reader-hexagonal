package com.jagt.mangareader.manga.infrastructure.output.client.dto.manga;

public record MangaRelationshipClientResponse(
        String id,
        String type,
        CoverAttributesClientResponse attributes
) {
}
