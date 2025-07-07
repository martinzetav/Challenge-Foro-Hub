package com.app.foro_hub.dto.response;

import com.app.foro_hub.model.UserEntity;

public record AuthorDTO(
        Long id,
        String name,
        String email
) {
    public AuthorDTO(UserEntity author){
        this(
                author.getId(),
                author.getName(),
                author.getEmail()
        );
    }
}
