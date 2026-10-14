package com.github.carlosvillarinho.dto;

import com.github.carlosvillarinho.entities.DadoColetado;
import com.github.carlosvillarinho.entities.TipoDado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DadoColetadoResponseDTO {
    private Long dadoId;
    private String sondaNome;
    private String agenciaEspacial;
    private LocalDate dataColeta;
    private TipoDado tipoDado;
    private BigDecimal valorMedido;
    private String unidadeMedida;

    public DadoColetadoResponseDTO(DadoColetado dado) {
        this.dadoId = dado.getDadoId();
        this.sondaNome = dado.getSondaNome();
        this.agenciaEspacial = dado.getAgenciaEspacial();
        this.dataColeta = dado.getDataColeta();
        this.tipoDado = dado.getTipoDado();
        this.valorMedido = dado.getValorMedido();
        this.unidadeMedida = dado.getUnidadeMedida();
    }
}
