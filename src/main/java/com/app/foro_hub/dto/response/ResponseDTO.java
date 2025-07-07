package com.app.foro_hub.dto.response;

import com.app.foro_hub.model.Response;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long id,
        String messsage,
        TopicResponseDTO topic,
        LocalDateTime creationDate,
        AuthorDTO author,
        String solution
) {
    public ResponseDTO(Response response){
        this(
                response.getId(),
                response.getMessage(),
                new TopicResponseDTO(response.getTopic()),
                response.getCreationDate(),
                new AuthorDTO(response.getAuthor()),
                response.getSolution()
        );
    }
}
