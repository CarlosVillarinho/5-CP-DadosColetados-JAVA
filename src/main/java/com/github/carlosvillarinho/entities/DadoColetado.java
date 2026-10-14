package com.github.carlosvillarinho.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "dadoId")
@Entity
@Table(name = "tb_dado_coletado")
public class DadoColetado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dadoId;

    @Column(nullable = false, length = 100)
    private String sondaNome;

    @Column(nullable = false, length = 100)
    private String agenciaEspacial;

    @Column(nullable = false)
    private LocalDate dataColeta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDado tipoDado;

    @Column(nullable = false)
    private BigDecimal valorMedido;

    @Column(nullable = false, length = 100)
    private String unidadeMedida;
}
