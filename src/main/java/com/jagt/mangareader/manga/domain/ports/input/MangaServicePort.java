package com.jagt.mangareader.manga.domain.ports.input;

import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.shared.domain.PaginatedResult;

public interface MangaServicePort {
    Manga searchMangaById(String id, String language);
    PaginatedResult<Manga> searchMangas(String title, int offset, int limit, boolean nsfw, String language);
    PaginatedResult<Feed> searchMangaFeeds(String id, int offset, int limit, boolean nsfw, String language);
    Chapter searchChapter(String id);
}
