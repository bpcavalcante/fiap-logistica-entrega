package com.fiap.logistica_entrega.infrastructure;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.converter.EntregaEntityConverter;
import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;
import com.fiap.logistica_entrega.infrastructure.exceptions.PersistedDatabaseException;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import com.fiap.logistica_entrega.infrastructure.jpa.EntregaRepository;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
@RequiredArgsConstructor
public class EntregaRepositoryPortImpl implements EntregaRepositoryPort {

    public static final String DUPLICATED_ENTRY_ERROR_MESSAGE = "Duplicate entry";
    public static final String FAILED_SAVE_TO_DATABASE_ERROR_MESSAGE = "Failed to save on database";
    public static final String ENTREGA_NOT_FOUND_ERROR_MESSAGE = "Entrega not found: ";

    private final EntregaRepository entregaRepository;
    private final EntregaEntityConverter entregaConverter = EntregaEntityConverter.getInstance();


    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public EntregaDatabaseDto findById(long id) {
        EntregaEntity entregaEntity = getById(id);
        return entregaConverter.toDatabaseDto(entregaEntity);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EntregaDatabaseDto> findAll() {
        List<EntregaEntity> entregaEntities = entregaRepository.findAll();
        return entregaConverter.toDatabaseListDto(entregaEntities);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EntregaDatabaseDto> findByCep(String cep) {
        List<EntregaEntity> entregaEntities = entregaRepository.findByCep(cep);
        return entregaConverter.toDatabaseListDto(entregaEntities);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public EntregaDatabaseDto save(EntregaDatabaseDto entregaDto) {
        log.debug("Saving entrega");

        EntregaEntity entity = entregaConverter.toEntity(entregaDto);

        try {
            return entregaConverter.toDatabaseDto(entregaRepository.save(entity));
        } catch (DataIntegrityViolationException e) {
            throw new PersistedDatabaseException(DUPLICATED_ENTRY_ERROR_MESSAGE);
        } catch (DataAccessException e) {
            throw new PersistedDatabaseException(FAILED_SAVE_TO_DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional
    public EntregaDatabaseDto update(long id, EntregaDatabaseDto entregaDatabaseDto) {
        log.debug("Editing entrega with id: {}", id);
        EntregaEntity entregaEntity = getById(id);
        updateEntityWithDto(entregaEntity, entregaDatabaseDto);
        try {
            return entregaConverter.toDatabaseDto(entregaRepository.save(entregaEntity));
        } catch (DataIntegrityViolationException e) {
            throw new PersistedDatabaseException(DUPLICATED_ENTRY_ERROR_MESSAGE);
        } catch (DataAccessException e) {
            throw new PersistedDatabaseException(FAILED_SAVE_TO_DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Deleting entrega with id: {}", id);
        EntregaEntity entregaEntity = getById(id);

        try {
            entregaRepository.delete(entregaEntity);
        } catch (DataIntegrityViolationException e) {
            throw new PersistedDatabaseException(DUPLICATED_ENTRY_ERROR_MESSAGE);
        } catch (DataAccessException e) {
            throw new PersistedDatabaseException(FAILED_SAVE_TO_DATABASE_ERROR_MESSAGE);
        }
    }

    private EntregaEntity getById(long id) {
        return entregaRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundDatabaseException(ENTREGA_NOT_FOUND_ERROR_MESSAGE + id));
    }

    private void updateEntityWithDto(EntregaEntity entregaEntity, EntregaDatabaseDto entregaDatabaseDto) {
        entregaEntity.setIdPedido(entregaDatabaseDto.getIdPedido());
        entregaEntity.setLatitude(entregaDatabaseDto.getLatitude());
        entregaEntity.setLongitude(entregaDatabaseDto.getLongitude());
        entregaEntity.setCep(entregaDatabaseDto.getCep());
    }

}
