package com.fiap.logistica_entrega.domain.ports.inbound;

import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;

public interface CreateEntregaUseCase {
  EntregaDto create(EntregaDto productDto);
}
