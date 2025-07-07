package com.app.foro_hub.service;

import com.app.foro_hub.dto.request.ResponseRequestDTO;
import com.app.foro_hub.dto.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IResponseService {
    ResponseDTO createResponse(ResponseRequestDTO responseRequestDTO);
    Page<ResponseDTO> findAllResponses(Pageable pageable);
    ResponseDTO findResponseById(Long responseId);
    ResponseDTO updateResponse(Long responseId, ResponseRequestDTO responseRequestDTO);
    void deleteResponse(Long responseId);
}
