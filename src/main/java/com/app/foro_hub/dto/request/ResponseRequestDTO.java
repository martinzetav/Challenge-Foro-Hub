package com.app.foro_hub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseRequestDTO(
        @NotBlank(message = "The message field is required")
        String message,
        @NotNull(message = "The topicId is required")
        Long topicId,
        @NotNull(message = "The authorId is required")
        Long authorId,
        @NotBlank(message = "The solution field is required")
        String solution
) {}
