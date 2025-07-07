package com.app.foro_hub.dto.response;

import com.app.foro_hub.model.Topic;

import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        String status,
        LocalDateTime creationDate,
        AuthorDTO author,
        CourseDTO courseDTO
) {
    public TopicResponseDTO(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getCreationDate(),
                new AuthorDTO(topic.getAuthor()),
                new CourseDTO(topic.getCourse())
        );
    }
}
