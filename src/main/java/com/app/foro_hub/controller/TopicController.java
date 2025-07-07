package com.app.foro_hub.controller;

import com.app.foro_hub.dto.request.TopicRequestDTO;
import com.app.foro_hub.dto.response.TopicResponseDTO;
import com.app.foro_hub.service.ITopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> findAllTopics(@PageableDefault(size = 10, sort = {"creationDate"}, direction = Sort.Direction.DESC)
                                                                Pageable pageable){
        return ResponseEntity.ok(topicService.findAllTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> findTopicById(@PathVariable Long id){
        return ResponseEntity.ok(topicService.findTopicById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id,
                                                        @RequestBody @Valid TopicRequestDTO topicRequestDTO){
        return ResponseEntity.ok(topicService.updateTopic(id, topicRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id){
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
