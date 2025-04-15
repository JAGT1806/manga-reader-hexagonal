package com.jagt.mangareader.role.infrastructure.input.rest.request;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank(message = "El nombre del rol es obligatorio")
        String name
) {
}
