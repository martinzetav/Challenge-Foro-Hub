package com.app.foro_hub.dto.response;

import com.app.foro_hub.model.Course;

public record CourseDTO(
        Long id,
        String name,
        String category
) {
    public CourseDTO(Course course){
        this(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
    }
}
