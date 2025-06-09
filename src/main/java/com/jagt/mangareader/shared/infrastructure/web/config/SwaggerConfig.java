package com.jagt.mangareader.shared.infrastructure.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        StringSchema datetimeSchema = new StringSchema();
        datetimeSchema.setFormat(DATE_TIME_FORMAT);
        datetimeSchema.example(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        SpringDocUtils.getConfig().replaceWithSchema(LocalDateTime.class, datetimeSchema);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Manga Reader Hexagonal API")
                        .version("v1")
                        .description("Documentación de la API de Manga Reader"));
    }

    @Bean
    public OpenApiCustomizer languageHeaderCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            Parameter langHeader = new HeaderParameter()
                    .name("Accept-Language")
                    .description("Idioma preferido para la respuesta (por ejemplo: es, en, fr)")
                    .required(false)
                    .schema(new StringSchema()._default("es"));
            operation.addParametersItem(langHeader);
        }));
    }
}
