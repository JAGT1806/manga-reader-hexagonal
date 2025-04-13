package com.jagt.mangareader.manga.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {
    private String baseUrl;
    private String hash;
    private List<String> data;
    private List<String> dataSaver;
}
