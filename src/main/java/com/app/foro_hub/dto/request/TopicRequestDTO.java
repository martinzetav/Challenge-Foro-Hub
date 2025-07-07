package com.app.foro_hub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotBlank(message = "The title field is required")
        String title,
        @NotBlank(message = "The message field is required")
        String message,
        @NotNull(message = "The userId is required")
        Long userId,
        @NotNull(message = "The courseId is required")
        Long courseId
) {}
