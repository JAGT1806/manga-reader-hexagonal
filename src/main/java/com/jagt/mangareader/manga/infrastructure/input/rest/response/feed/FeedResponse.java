package com.jagt.mangareader.manga.infrastructure.input.rest.response.feed;

import java.util.List;

public record FeedResponse(
        List<FeedDTO> data,
        int offset,
        int limit,
        int total
) {
}
