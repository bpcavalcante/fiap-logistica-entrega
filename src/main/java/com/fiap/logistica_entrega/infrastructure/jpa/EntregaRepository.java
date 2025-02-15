package com.fiap.logistica_entrega.infrastructure.jpa;

import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {}
