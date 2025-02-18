package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.exceptions.DatabaseErrorException;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import com.fiap.logistica_entrega.domain.ports.inbound.FindEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.converter.EntregaConverterInbound;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FindEntrega implements FindEntregaUseCase {
    private final EntregaRepositoryPort entregaRepositoryPort;
    private final EntregaConverterInbound entregaConverter = EntregaConverterInbound.getInstance();

    @Override
    public EntregaDto findById(Long id) {
        try {
            return entregaConverter.toEntregaDto(entregaRepositoryPort.findById(id));
        } catch (ResourceNotFoundDatabaseException e) {
            throw new ResourceNotFoundException("Entrega not found");
        }
    }

    @Override
    public List<EntregaDto> findAll() {
        Optional<List<EntregaDatabaseDto>> entregasDatabaseOpt = Optional.ofNullable(entregaRepositoryPort.findAll());

        return entregasDatabaseOpt
                .filter(entregas -> !entregas.isEmpty())
                .map(entregas -> entregas.stream()
                        .map(entregaConverter::toEntregaDto)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }


    @Override
    public List<EntregaDto> findByCep(String cep) {
        try {
            Optional<List<EntregaDatabaseDto>> entregaDatabaseOpt = Optional.ofNullable(entregaRepositoryPort.findByCep(cep));

            return entregaDatabaseOpt
                    .map(entregas -> entregas.stream()
                            .map(entregaConverter::toEntregaDto)
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList());
        } catch (Exception e) {
            throw new DatabaseErrorException("Error while fetching delivery by CEP: " + e.getMessage());
        }
    }


}
