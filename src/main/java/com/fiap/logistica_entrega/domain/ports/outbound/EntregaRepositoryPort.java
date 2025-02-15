package com.fiap.logistica_entrega.domain.ports.outbound;

import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.exceptions.PersistedDatabaseException;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import java.util.List;

public interface EntregaRepositoryPort {

  EntregaDatabaseDto save(EntregaDatabaseDto entrega) throws PersistedDatabaseException;

//  EntregaDatabaseDto findById(long id) throws ResourceNotFoundDatabaseException;
//
//  List<EntregaDatabaseDto> findAll(EntregaDatabaseDto entrega);
//
//  EntregaDatabaseDto update(long id, EntregaDatabaseDto entrega);
//
//  void delete(long id);
}
