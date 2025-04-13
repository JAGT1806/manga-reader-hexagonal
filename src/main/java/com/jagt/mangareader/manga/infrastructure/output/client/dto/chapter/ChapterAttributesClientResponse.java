package com.jagt.mangareader.manga.infrastructure.output.client.dto.chapter;

import java.util.List;

public record ChapterAttributesClientResponse(
        String hash,
        List<String> data,
        List<String> dataSaver
) {
}
