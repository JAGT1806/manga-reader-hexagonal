package com.jagt.mangareader.manga.domain.ports.output;

import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.shared.domain.PaginatedResult;

import java.util.List;

public interface MangaClientPort {
    Manga searchMangaById(String id, List<String> languages);
    PaginatedResult<Manga> searchMangas(String title, int offset, int limit, List<String> contentRating, List<String> languages);
    PaginatedResult<Feed> searchMangaFeeds(String mangaId, int offset, int limit, List<String> contentRating, List<String> language);
    Chapter searchChapter(String chapterId);
}
