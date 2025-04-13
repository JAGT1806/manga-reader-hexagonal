package com.jagt.mangareader.manga.infrastructure.output.client.dto.manga;

import java.util.List;

public record MangaListClientResponse(
        List<MangaDataClientResponse> data,
        int offset,
        int limit,
        long total
) {
}
