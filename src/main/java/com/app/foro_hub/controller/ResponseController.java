package com.app.foro_hub.controller;

import com.app.foro_hub.dto.request.ResponseRequestDTO;
import com.app.foro_hub.dto.response.ResponseDTO;
import com.app.foro_hub.service.IResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final IResponseService responseService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse(@RequestBody @Valid ResponseRequestDTO responseRequestDTO,
                                                      UriComponentsBuilder uriComponentsBuilder){
        ResponseDTO responseDTO = responseService.createResponse(responseRequestDTO);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseDTO>> findAllResponses(Pageable pageable){
        return ResponseEntity.ok(responseService.findAllResponses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findResponseById(@PathVariable Long id){
        return ResponseEntity.ok(responseService.findResponseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable Long id,
                                                      @RequestBody @Valid ResponseRequestDTO responseRequestDTO){
        return ResponseEntity.ok(responseService.updateResponse(id, responseRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id){
        responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
