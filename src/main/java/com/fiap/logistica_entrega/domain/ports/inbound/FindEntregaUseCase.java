package com.fiap.logistica_entrega.domain.ports.inbound;

import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;

import java.util.List;

public interface FindEntregaUseCase {
  EntregaDto findById(Long id);
  List<EntregaDto> findAll();
  List<EntregaDto> findByCep(String cep);
}
