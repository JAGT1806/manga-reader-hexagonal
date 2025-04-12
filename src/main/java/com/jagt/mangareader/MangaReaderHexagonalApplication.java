package com.jagt.mangareader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MangaReaderHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangaReaderHexagonalApplication.class, args);
    }

}
