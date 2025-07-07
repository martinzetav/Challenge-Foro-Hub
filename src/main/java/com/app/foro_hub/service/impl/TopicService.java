package com.app.foro_hub.service.impl;

import com.app.foro_hub.dto.request.TopicRequestDTO;
import com.app.foro_hub.dto.response.TopicResponseDTO;
import com.app.foro_hub.exception.ResourceAlreadyExistsException;
import com.app.foro_hub.exception.ResourceNotFoundException;
import com.app.foro_hub.model.Course;
import com.app.foro_hub.model.Topic;
import com.app.foro_hub.model.UserEntity;
import com.app.foro_hub.repository.ICourseRepository;
import com.app.foro_hub.repository.ITopicRepository;
import com.app.foro_hub.repository.IUserEntityRepository;
import com.app.foro_hub.service.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {

    private final ITopicRepository topicRepository;
    private final ICourseRepository courseRepository;
    private final IUserEntityRepository userEntityRepository;

    @Override
    public TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO) {
        Course course = courseRepository.findById(topicRequestDTO.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The course with id " + topicRequestDTO.courseId() + " not found."
                ));
        UserEntity author = userEntityRepository.findById(topicRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The author with id " + topicRequestDTO.userId() + " not found."
                ));

        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessageIgnoreCase(topicRequestDTO.title(),
                topicRequestDTO.message());

        if(existingTopic.isPresent()){
            throw new ResourceAlreadyExistsException("A topic with the same title and message already exists.");
        }

        Topic topic = toEntity(topicRequestDTO);
        Topic savedTopic = topicRepository.save(topic);
        return new TopicResponseDTO(savedTopic);
    }

    private Topic toEntity(TopicRequestDTO topicRequestDTO){
        Course course = courseRepository.getReferenceById(topicRequestDTO.courseId());
        UserEntity author = userEntityRepository.getReferenceById(topicRequestDTO.userId());
        return Topic.builder()
                .title(topicRequestDTO.title())
                .message(topicRequestDTO.message())
                .creationDate(LocalDateTime.now())
                .status("OPEN")
                .author(author)
                .course(course)
                .build();
    }
}
