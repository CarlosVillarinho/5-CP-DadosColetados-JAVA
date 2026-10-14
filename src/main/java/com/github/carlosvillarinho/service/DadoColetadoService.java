package com.github.carlosvillarinho.service;

import com.github.carlosvillarinho.dto.DadoColetadoRequestDTO;
import com.github.carlosvillarinho.dto.DadoColetadoResponseDTO;
import com.github.carlosvillarinho.entities.DadoColetado;
import com.github.carlosvillarinho.exceptions.ResourceNotFoundException;
import com.github.carlosvillarinho.repository.DadoColetadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DadoColetadoService {
    @Autowired
    private DadoColetadoRepository dadoRepository;

    @Transactional(readOnly = true)
    public List<DadoColetadoResponseDTO> findAllDados() {
        List<DadoColetado> dados = dadoRepository.findAll();
        return dados.stream().map(DadoColetadoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public DadoColetadoResponseDTO findDadoById(Long id) {
        DadoColetado dado = dadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new DadoColetadoResponseDTO(dado);
    }

    @Transactional
    public DadoColetadoResponseDTO saveDado(DadoColetadoRequestDTO requestDTO) {
        DadoColetado dado = new DadoColetado();
        copyDtoToDado(requestDTO, dado);
        dado = dadoRepository.save(dado);
        return new DadoColetadoResponseDTO(dado);
    }

    @Transactional
    public DadoColetadoResponseDTO updateDado(Long id, DadoColetadoRequestDTO requestDTO) {
        try {
            DadoColetado dado = dadoRepository.getReferenceById(id);
            copyDtoToDado(requestDTO, dado);
            dado = dadoRepository.save(dado);
            return new DadoColetadoResponseDTO(dado);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteDadoById(Long id) {
        if (!dadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        dadoRepository.deleteById(id);
    }

    private void copyDtoToDado(DadoColetadoRequestDTO requestDTO, DadoColetado dado) {
        dado.setSondaNome(requestDTO.getSondaNome());
        dado.setAgenciaEspacial(requestDTO.getAgenciaEspacial());
        dado.setDataColeta(requestDTO.getDataColeta());
        dado.setTipoDado(requestDTO.getTipoDado());
        dado.setValorMedido(requestDTO.getValorMedido());
        dado.setUnidadeMedida(requestDTO.getUnidadeMedida());
    }
}
