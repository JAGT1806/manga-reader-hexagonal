package com.jagt.mangareader.manga.infrastructure.output.client.mapper;

import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.domain.model.Manga;
import com.jagt.mangareader.manga.infrastructure.output.client.dto.chapter.ChapterClientResponse;
import com.jagt.mangareader.manga.infrastructure.output.client.dto.feed.FeedListClientResponse;
import com.jagt.mangareader.manga.infrastructure.output.client.dto.feed.FeedClientResponse;
import com.jagt.mangareader.manga.infrastructure.output.client.dto.manga.*;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MangaClientMapper {
    @Mapping(target = "title", source = "data.attributes.title", qualifiedByName = "getEnglishTitle")
    @Mapping(target = "description", source = "data.attributes.description", qualifiedByName = "getDescription")
    @Mapping(target = "coverId", source = "data.relationships", qualifiedByName = "getCoverId")
    @Mapping(target = "fileName", source = "data.relationships", qualifiedByName = "getFileName")
    Manga toManga(MangaClientResponse response);

    @Mapping(target = "title", source = "attributes.title", qualifiedByName = "getEnglishTitle")
    @Mapping(target = "description", source = "attributes.description", qualifiedByName = "getDescription")
    @Mapping(target = "coverId", source = "relationships", qualifiedByName = "getCoverId")
    @Mapping(target = "fileName", source = "relationships", qualifiedByName = "getFileName")
    Manga toManga(MangaDataClientResponse response);

    PaginatedResult<Manga> toPaginatedResult(MangaListClientResponse response);


    @Mapping(target = "volume", source = "attributes.volume")
    @Mapping(target = "chapter", source = "attributes.chapter")
    @Mapping(target = "title", source = "attributes.title")
    @Mapping(target = "translatedLanguage", source = "attributes.translatedLanguage")
    @Mapping(target = "pages", source = "attributes.pages")
    Feed toFeed(FeedClientResponse response);

    PaginatedResult<Feed> toPaginatedResult(FeedListClientResponse response);

    @Mapping(target = "baseUrl", source = "baseUrl")
    @Mapping(target = "hash", source = "chapter.hash")
    @Mapping(target = "data", source = "chapter.data")
    @Mapping(target = "dataSaver", source = "chapter.dataSaver")
    Chapter toChapter(ChapterClientResponse response);

    @Named("getEnglishTitle")
    default String getEnglishTitle(Map<String, String> title) {
        return title != null ? title.get("en") : null;
    }

    @Named("getDescription")
    default String getDescription(Map<String, String> description) {
        if (description == null) return null;
        if (description.containsKey("es")) return description.get("es") != null ? description.get("es") : description.get("es-la");
        if (description.containsKey("en")) return description.get("en");
        return description.values().stream().findFirst().orElse(null);
    }

    @Named("getCoverId")
    default String getCoverId(List<MangaRelationshipClientResponse> relationships) {
        return relationships.stream()
                .filter(r -> "cover_art".equals(r.type()))
                .map(MangaRelationshipClientResponse::id)
                .findFirst().orElse(null);
    }

    @Named("getFileName")
    default String getFileName(List<MangaRelationshipClientResponse> relationships) {
        return relationships.stream()
                .filter(r -> "cover_art".equals(r.type()) && r.attributes() != null)
                .map(r -> r.attributes().fileName())
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }
}
