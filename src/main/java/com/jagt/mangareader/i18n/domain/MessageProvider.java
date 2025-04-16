package com.jagt.mangareader.i18n.domain;

public interface MessageProvider {
    String getMessage(String code);

    String getMessage(String code, Object[] args);
}
