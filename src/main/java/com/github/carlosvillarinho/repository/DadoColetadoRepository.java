package com.github.carlosvillarinho.repository;

import com.github.carlosvillarinho.entities.DadoColetado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadoColetadoRepository extends JpaRepository <DadoColetado, Long> {
}
