package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import com.fiap.logistica_entrega.domain.ports.inbound.UpdateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.converter.EntregaConverterInbound;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateEntrega implements UpdateEntregaUseCase {
    private final EntregaRepositoryPort entregaRepositoryPort;
    private final EntregaConverterInbound entregaConverter = EntregaConverterInbound.getInstance();

    @Override
    public EntregaDto update(Long id, EntregaDto entregaDto) {
        var entrega = entregaConverter.toDomain(entregaDto);
        final var entregaDatabaseDto = entregaConverter.toDatabaseDTO(entrega);
        try {
            return entregaConverter.toEntregaDto(
                    entregaRepositoryPort.update(id, entregaDatabaseDto)
            );
        } catch (ResourceNotFoundDatabaseException e) {
            throw new ResourceNotFoundException("Entrega not found");
        }
    }
}
