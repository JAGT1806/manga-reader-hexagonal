package com.jagt.mangareader.manga.infrastructure.input.rest.mapper;

import com.jagt.mangareader.manga.domain.model.Chapter;
import com.jagt.mangareader.manga.domain.model.Feed;
import com.jagt.mangareader.manga.infrastructure.input.rest.response.chapter.ChapterResponse;
import com.jagt.mangareader.manga.infrastructure.input.rest.response.feed.FeedDTO;
import com.jagt.mangareader.manga.infrastructure.input.rest.response.feed.FeedResponse;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MangaRestMapper {
    FeedResponse toFeedResponse(PaginatedResult<Feed> feedPaginatedResult);

    @Mapping(target = "attributes.volume", source = "volume")
    @Mapping(target = "attributes.chapter", source = "chapter")
    @Mapping(target = "attributes.title", source = "title")
    @Mapping(target = "attributes.language", source = "translatedLanguage")
    @Mapping(target = "attributes.pages", source = "pages")
    FeedDTO toFeedDTO(Feed feed);

    @Mapping(source = ".", target = "data", qualifiedByName = "buildData")
    @Mapping(source = ".", target = "dataSaver", qualifiedByName = "buildDataSaver")
    ChapterResponse toChapterResponse(Chapter chapter);

    @Named("buildData")
    default List<String> buildData(Chapter chapter) {
        return buildUrl(chapter.getBaseUrl(), chapter.getHash(), chapter.getData(), "data");
    }

    @Named("buildDataSaver")
    default List<String> buildDataSaver(Chapter chapter) {
        return buildUrl(chapter.getBaseUrl(), chapter.getHash(), chapter.getDataSaver(), "data-saver");
    }

    private static List<String> buildUrl(String baseUrl, String hash, List<String> data, String type) {
        if (data == null || data.isEmpty()) return List.of();
        return data.stream()
                .map(img -> String.format("%s/%s/%s/%s", baseUrl, type, hash, img))
                .toList();
    }
}
