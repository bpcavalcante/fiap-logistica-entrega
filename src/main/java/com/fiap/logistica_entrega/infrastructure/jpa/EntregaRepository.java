package com.fiap.logistica_entrega.infrastructure.jpa;

import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {

    @Query("SELECT e FROM EntregaEntity e WHERE e.cep LIKE %:cep%")
    List<EntregaEntity> findByCep(@Param("cep") String cep);
}