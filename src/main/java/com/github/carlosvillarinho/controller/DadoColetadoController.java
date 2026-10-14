package com.github.carlosvillarinho.controller;

import com.github.carlosvillarinho.dto.DadoColetadoRequestDTO;
import com.github.carlosvillarinho.dto.DadoColetadoResponseDTO;
import com.github.carlosvillarinho.service.DadoColetadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dados-coletados")
public class DadoColetadoController {
    @Autowired
    private DadoColetadoService dadoService;

    @GetMapping
    public ResponseEntity<List<DadoColetadoResponseDTO>> getAllDados() {
        List<DadoColetadoResponseDTO> list = dadoService.findAllDados();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadoColetadoResponseDTO> getDadoById(@PathVariable Long id) {
        DadoColetadoResponseDTO dadoDTO = dadoService.findDadoById(id);
        return ResponseEntity.ok(dadoDTO);
    }

    @PostMapping
    public ResponseEntity<DadoColetadoResponseDTO> createDado(@RequestBody @Valid DadoColetadoRequestDTO requestDTO) {
        DadoColetadoResponseDTO dadoDTO = dadoService.saveDado(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dadoDTO.getDadoId())
                .toUri();
        return ResponseEntity.created(uri).body(dadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadoColetadoResponseDTO> updateDado(@PathVariable Long id,
                                                              @RequestBody @Valid
                                                              DadoColetadoRequestDTO requestDTO) {
        DadoColetadoResponseDTO dadoDTO = dadoService.updateDado(id, requestDTO);
        return ResponseEntity.ok(dadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDado(@PathVariable Long id) {
        dadoService.deleteDadoById(id);
        return ResponseEntity.noContent().build();
    }
}
