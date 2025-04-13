package com.jagt.mangareader.manga.application.service;

import com.jagt.mangareader.manga.domain.ports.input.MangaServicePort;
import com.jagt.mangareader.manga.domain.ports.output.MangaClientPort;
import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService implements MangaServicePort {
    private final MangaClientPort mangaClientPort;

    private static final String INCLUDES = "cover_art";
    private static final List<String> SAFE_CONTENT = List.of("safe", "suggestive");
    private static final List<String> NSFW_CONTENT = List.of("erotica", "pornographic");

    @Override
    public Manga searchMangaById(String id, String language) {
        return mangaClientPort.searchMangaById(id, language);
    }

    @Override
    public PaginatedResult<Manga> searchMangas(String title, int offset, int limit, boolean nsfw, String language) {
        List<String> contentRatingFilters = new ArrayList<>(SAFE_CONTENT);
        if(nsfw) contentRatingFilters.addAll(NSFW_CONTENT);
        List<String> availableTranslatedLanguage = getLanguage(language);

        return mangaClientPort.searchMangas(title, INCLUDES, offset, limit, contentRatingFilters, availableTranslatedLanguage);
    }

    @Override
    public PaginatedResult<Feed> searchMangaFeeds(String id, int offset, int limit, boolean nsfw, String language) {
        List<String> contentRatingFilters = new ArrayList<>(SAFE_CONTENT);
        if(nsfw) contentRatingFilters.addAll(NSFW_CONTENT);
        List<String> availableTranslatedLanguage = getLanguage(language);

        return mangaClientPort.searchMangaFeeds(id, offset, limit, contentRatingFilters, availableTranslatedLanguage);
    }

    @Override
    public Chapter searchChapter(String id) {
        return mangaClientPort.searchChapter(id);
    }

    private List<String> getLanguage(String language) {
        return language.equals("es") ? List.of("es", "es-la") : List.of(language);
    }
}
