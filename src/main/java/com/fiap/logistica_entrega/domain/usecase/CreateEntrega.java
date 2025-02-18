package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.Entrega;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundErrorException;
import com.fiap.logistica_entrega.domain.ports.inbound.CreateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.converter.EntregaConverterInbound;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateEntrega implements CreateEntregaUseCase {
  private final EntregaRepositoryPort entregaRepositoryPort;
  private final EntregaConverterInbound entregaConverter = EntregaConverterInbound.getInstance();

  @Override
  public EntregaDto create(EntregaDto entregaDto) {
    log.debug("Saving entrega");
    Entrega entrega = entregaConverter.toDomain(entregaDto);
    EntregaDatabaseDto savedEntrega = saveEntrega(entrega);
    return entregaConverter.toEntregaDto(savedEntrega);
  }

  private EntregaDatabaseDto saveEntrega(Entrega entrega) {
    final var entregaDatabaseDto = entregaConverter.toDatabaseDTO(entrega);
    try {
      return entregaRepositoryPort.save(entregaDatabaseDto);
    } catch (ResourceNotFoundDatabaseException e) {
      log.error("Error saving entrega to repository: {}", e.getMessage());
      throw new ResourceNotFoundErrorException(e.getMessage());
    }
  }
}
