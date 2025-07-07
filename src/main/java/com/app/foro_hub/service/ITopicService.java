package com.app.foro_hub.service;

import com.app.foro_hub.dto.request.TopicRequestDTO;
import com.app.foro_hub.dto.response.TopicResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopicService {
    TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO);
    Page<TopicResponseDTO> findAllTopics(Pageable pageable);
}
