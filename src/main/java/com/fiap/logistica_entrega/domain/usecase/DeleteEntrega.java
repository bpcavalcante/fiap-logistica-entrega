package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import com.fiap.logistica_entrega.domain.ports.inbound.DeleteEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteEntrega implements DeleteEntregaUseCase {
    private final EntregaRepositoryPort entregaRepositoryPort;
    @Override
    public void delete(long id) {
        try {
            entregaRepositoryPort.delete(id);
        } catch (ResourceNotFoundDatabaseException e) {
            throw new ResourceNotFoundException("Entrega not found");
        }
    }
}
