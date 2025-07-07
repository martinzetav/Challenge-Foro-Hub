package com.app.foro_hub.service.impl;

import com.app.foro_hub.dto.request.ResponseRequestDTO;
import com.app.foro_hub.dto.response.ResponseDTO;
import com.app.foro_hub.exception.ResourceNotFoundException;
import com.app.foro_hub.model.Response;
import com.app.foro_hub.model.Topic;
import com.app.foro_hub.model.UserEntity;
import com.app.foro_hub.repository.IResponseRepository;
import com.app.foro_hub.repository.ITopicRepository;
import com.app.foro_hub.repository.IUserEntityRepository;
import com.app.foro_hub.service.IResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResponseService implements IResponseService {

    private final IResponseRepository responseRepository;
    private final ITopicRepository topicRepository;
    private final IUserEntityRepository userEntityRepository;

    @Override
    @Transactional
    public ResponseDTO createResponse(ResponseRequestDTO responseRequestDTO) {
        validateResponseEntities(responseRequestDTO);
        Response response = toEntity(responseRequestDTO);
        Response savedResponse = responseRepository.save(response);
        return new ResponseDTO(savedResponse);
    }

    @Override
    public Page<ResponseDTO> findAllResponses(Pageable pageable) {
        return responseRepository.findAll(pageable)
                .map(response -> new ResponseDTO(response));
    }


    @Override
    public ResponseDTO findResponseById(Long responseId) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The response with id " + responseId + " not found."
                ));
        return new ResponseDTO(response);
    }

    @Override
    @Transactional
    public ResponseDTO updateResponse(Long responseId, ResponseRequestDTO responseRequestDTO) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The response with id " + responseId + " not found."
                ));
        validateResponseEntities(responseRequestDTO);
        Response updatedResponse = toEntity(responseRequestDTO);

        response.setMessage(updatedResponse.getMessage());
        response.setTopic(updatedResponse.getTopic());
        response.setAuthor(updatedResponse.getAuthor());
        response.setSolution(updatedResponse.getSolution());

        return new ResponseDTO(response);
    }

    @Override
    @Transactional
    public void deleteResponse(Long responseId) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The response with id " + responseId + " not found."
                ));

        responseRepository.deleteById(response.getId());
    }

    private void validateResponseEntities(ResponseRequestDTO responseRequestDTO){
        Topic topic = topicRepository.findById(responseRequestDTO.topicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The topic with id " + responseRequestDTO.topicId() + " not found."
                ));
        UserEntity author = userEntityRepository.findById(responseRequestDTO.authorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The author with id " + responseRequestDTO.authorId() + " not found."
                ));
    }

    private Response toEntity(ResponseRequestDTO responseRequestDTO){
        Topic topic = topicRepository.getReferenceById(responseRequestDTO.topicId());
        UserEntity author = userEntityRepository.getReferenceById(responseRequestDTO.authorId());
        return Response.builder()
                .message(responseRequestDTO.message())
                .topic(topic)
                .creationDate(LocalDateTime.now())
                .author(author)
                .solution(responseRequestDTO.solution())
                .build();
    }
}
