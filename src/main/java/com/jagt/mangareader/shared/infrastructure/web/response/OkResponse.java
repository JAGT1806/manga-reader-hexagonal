package com.jagt.mangareader.shared.infrastructure.web.response;

public record OkResponse(
        String message,
        String code
) {
    public static OkResponse of(String code) {
        return new OkResponse("Ok", code);
    }
}
