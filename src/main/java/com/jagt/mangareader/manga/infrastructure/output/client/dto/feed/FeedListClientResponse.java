package com.jagt.mangareader.manga.infrastructure.output.client.dto.feed;

import java.util.List;

public record FeedListClientResponse(
        List<FeedClientResponse> data,
        int offset,
        int limit,
        long total
) {
}
