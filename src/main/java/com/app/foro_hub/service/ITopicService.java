package com.app.foro_hub.service;

import com.app.foro_hub.dto.request.TopicRequestDTO;
import com.app.foro_hub.dto.response.TopicResponseDTO;

public interface ITopicService {
    TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO);
}
