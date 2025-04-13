package com.jagt.mangareader.manga.infrastructure.input.rest.controller;

import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.manga.domain.ports.input.MangaServicePort;
import com.jagt.mangareader.manga.infrastructure.input.rest.mapper.MangaRestMapper;
import com.jagt.mangareader.manga.infrastructure.input.rest.response.chapter.ChapterResponse;
import com.jagt.mangareader.manga.infrastructure.input.rest.response.feed.FeedResponse;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
public class MangaController {
    private final MangaServicePort mangaServicePort;
    private final MangaRestMapper mapper;

    @GetMapping
    public ResponseEntity<PaginatedResult<Manga>> getMangas(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "12") int limit,
            @RequestParam(defaultValue = "false") boolean nsfw,
            @RequestHeader(value = "Accept-Language", defaultValue = "es") String language
    ) {
        return ResponseEntity.ok(mangaServicePort.searchMangas(title, offset, limit, nsfw, language));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(
            @PathVariable String id,
            @RequestHeader(value = "Accept-Language", defaultValue = "es") String language
    ) {
        return ResponseEntity.ok(mangaServicePort.searchMangaById(id, language.substring(0, 2).toLowerCase()));
    }

    @GetMapping("/{id}/feed")
    public ResponseEntity<FeedResponse> getMangaFeeds(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "100") int limit,
            @RequestParam(required = false, defaultValue = "false") boolean nsfw,
            @RequestHeader(value = "Accept-Language", defaultValue = "es") String language
    ) {
        return ResponseEntity.ok(mapper.toFeedResponse(mangaServicePort.searchMangaFeeds(id, offset, limit, nsfw, language)));
    }

    @GetMapping("/chapter/{id}")
    public ResponseEntity<ChapterResponse> getChapter(@PathVariable String id) {
        return ResponseEntity.ok(mapper.toChapterResponse(mangaServicePort.searchChapter(id)));
    }
}
