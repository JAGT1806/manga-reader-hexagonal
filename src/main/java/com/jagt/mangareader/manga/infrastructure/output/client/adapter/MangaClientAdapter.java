package com.jagt.mangareader.manga.infrastructure.output.client.adapter;

import com.jagt.mangareader.manga.domain.ports.output.MangaClientPort;
import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.manga.infrastructure.output.client.MangaDexClient;
import com.jagt.mangareader.manga.infrastructure.output.client.mapper.MangaClientMapper;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MangaClientAdapter implements MangaClientPort {
    private final MangaDexClient mangaDexClient;
    private final MangaClientMapper mapper;

    private static final String DEFAULT_INCLUDES = "cover_art";

    @Override
    public Manga searchMangaById(String id, List<String> languages) {
        return mapper.toManga(mangaDexClient.getMangaId(id, DEFAULT_INCLUDES), languages);
    }

    @Override
    public PaginatedResult<Manga> searchMangas(String title, int offset, int limit, List<String> contentRating, List<String> language) {
        return mapper.toPaginatedResult(mangaDexClient.getSearchManga(title, DEFAULT_INCLUDES, offset, limit, contentRating, language), language);
    }

    @Override
    public PaginatedResult<Feed> searchMangaFeeds(String mangaId, int offset, int limit, List<String> contentRating, List<String> language) {
        return mapper.toPaginatedResult(mangaDexClient.getMangaIdFeed(mangaId, offset, limit, contentRating, null, "asc", "asc", language));
    }

    @Override
    public Chapter searchChapter(String chapterId) {
        return mapper.toChapter(mangaDexClient.getAtHomeServerChapterId(chapterId));
    }

}
