package com.github.carlosvillarinho.dto;

import com.github.carlosvillarinho.entities.TipoDado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DadoColetadoRequestDTO {
    @NotBlank(message = "O nome da sonda é obrigatório")
    @Size(min = 3, max = 100, message = "O nome da sonda deve ter entre 3 e 100 caracteres")
    private String sondaNome;

    @NotBlank(message = "A agência espacial é obrigatória")
    @Size(min = 3, max = 100, message = "A agência espacial deve ter entre 3 e 100 caracteres")
    private String agenciaEspacial;

    @NotNull(message = "A data de coleta é obrigatória")
    @PastOrPresent(message = "A data de coleta deve ser menor ou igual à data atual")
    private LocalDate dataColeta;

    @NotNull(message = "O tipo do dado é obrigatório")
    private TipoDado tipoDado;

    @NotNull(message = "O valor medido é obrigatório")
    private BigDecimal valorMedido;

    @NotBlank(message = "A unidade de medida é obrigatória")
    @Size(min = 3, max = 100, message = "A unidade de medida deve ter entre 3 e 100 caracteres")
    private String unidadeMedida;
}
