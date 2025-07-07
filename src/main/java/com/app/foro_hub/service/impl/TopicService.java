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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {

    private final ITopicRepository topicRepository;
    private final ICourseRepository courseRepository;
    private final IUserEntityRepository userEntityRepository;

    @Override
    @Transactional
    public TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO) {
        validateTopicEntities(topicRequestDTO);
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessageIgnoreCase(topicRequestDTO.title(),
                topicRequestDTO.message());

        if(existingTopic.isPresent()){
            throw new ResourceAlreadyExistsException("A topic with the same title and message already exists.");
        }
        Topic topic = toEntity(topicRequestDTO);
        Topic savedTopic = topicRepository.save(topic);
        return new TopicResponseDTO(savedTopic);
    }

    @Override
    public Page<TopicResponseDTO> findAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(topic -> new TopicResponseDTO(topic));
    }

    @Override
    public TopicResponseDTO findTopicById(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("The topic with id " + topicId + " not found."));

        return new TopicResponseDTO(topic);
    }

    @Override
    @Transactional
    public TopicResponseDTO updateTopic(Long topicId, TopicRequestDTO topicRequestDTO) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("The topic with id " + topicId + " not found."));

        validateTopicEntities(topicRequestDTO);
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessageIgnoreCase(topicRequestDTO.title(),
                topicRequestDTO.message());

        if(existingTopic.isPresent() && !existingTopic.get().getId().equals(topicId)){
            throw new ResourceAlreadyExistsException("A topic with the same title and message already exists.");
        }
        Topic updatedTopic = toEntity(topicRequestDTO);
        topic.setTitle(updatedTopic.getTitle());
        topic.setMessage(updatedTopic.getMessage());
        topic.setAuthor(updatedTopic.getAuthor());
        topic.setCourse(updatedTopic.getCourse());

        return new TopicResponseDTO(topic);
    }

    @Override
    @Transactional
    public void deleteTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("The topic with id " + topicId + " not found."));

        topicRepository.deleteById(topic.getId());
    }

    private void validateTopicEntities(TopicRequestDTO topicRequestDTO){
        Course course = courseRepository.findById(topicRequestDTO.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The course with id " + topicRequestDTO.courseId() + " not found."
                ));
        UserEntity author = userEntityRepository.findById(topicRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The author with id " + topicRequestDTO.userId() + " not found."
                ));
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
