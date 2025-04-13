package com.jagt.mangareader.manga.infrastructure.output.client.dto.manga;

import java.util.List;

public record MangaDataClientResponse(
        String id,
        MangaAttributesClientResponse attributes,
        List<MangaRelationshipClientResponse> relationships
) {
}
