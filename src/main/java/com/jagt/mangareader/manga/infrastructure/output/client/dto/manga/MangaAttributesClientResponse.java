package com.jagt.mangareader.manga.infrastructure.output.client.dto.manga;

import java.util.Map;

public record MangaAttributesClientResponse(
        Map<String, String> title,
        Map<String, String> description
) {
}
