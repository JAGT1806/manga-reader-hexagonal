package com.jagt.mangareader.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResult<T> {
    private List<T> data;
    private int offset;
    private int limit;
    private long total;
}
