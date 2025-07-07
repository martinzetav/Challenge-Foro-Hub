package com.app.foro_hub.controller;

import com.app.foro_hub.dto.request.TopicRequestDTO;
import com.app.foro_hub.dto.response.TopicResponseDTO;
import com.app.foro_hub.service.ITopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final ITopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicRequestDTO topicRequestDTO,
                                                        UriComponentsBuilder uriComponentsBuilder){
        TopicResponseDTO topicResponseDTO = topicService.createTopic(topicRequestDTO);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(topicResponseDTO);

    }

}
