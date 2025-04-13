package com.jagt.mangareader.manga.infrastructure.input.rest.response.chapter;

import java.util.List;

public record ChapterResponse(
        List<String> data,
        List<String> dataSaver
) {
}
