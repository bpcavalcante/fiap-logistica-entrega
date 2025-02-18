package com.fiap.logistica_entrega.infrastructure;

import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;
import com.fiap.logistica_entrega.infrastructure.exceptions.PersistedDatabaseException;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import com.fiap.logistica_entrega.infrastructure.jpa.EntregaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaRepositoryPortImplTest {

    @Mock
    private EntregaRepository entregaRepository;

    private EntregaRepositoryPortImpl entregaRepositoryPort;


    final Instant now = Instant.now();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        entregaRepositoryPort = new EntregaRepositoryPortImpl(entregaRepository);
    }

    @Test
    public void testFindById_Success() {
        // Arrange
        Long id = 1L;
        EntregaEntity entity = new EntregaEntity(id, 123L, 90.0, 45.0, "12345-678", now, null);
        EntregaDatabaseDto expected = new EntregaDatabaseDto(id, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        EntregaDatabaseDto result = entregaRepositoryPort.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected.getIdPedido(), result.getIdPedido());
        assertEquals(expected.getLatitude(), result.getLatitude());
        assertEquals(expected.getLongitude(), result.getLongitude());
        assertEquals(expected.getCep(), result.getCep());
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        Long id = 1L;
        when(entregaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundDatabaseException exception = assertThrows(ResourceNotFoundDatabaseException.class, () -> {
            entregaRepositoryPort.findById(id);
        });

        assertEquals("Entrega not found: 1", exception.getMessage());
    }

    @Test
    public void testSave_Success() {
        // Arrange
        EntregaDatabaseDto dto = new EntregaDatabaseDto(1L, 123L, 90.0, 45.00, "12345-678", now, null);
        EntregaEntity entity = new EntregaEntity(1L, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.save(any(EntregaEntity.class))).thenReturn(entity);

        // Act
        EntregaDatabaseDto result = entregaRepositoryPort.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getIdPedido(), result.getIdPedido());
        assertEquals(dto.getLatitude(), result.getLatitude());
        assertEquals(dto.getLongitude(), result.getLongitude());
        assertEquals(dto.getCep(), result.getCep());
    }

    @Test
    public void testSave_DuplicateEntry() {
        // Arrange
        EntregaDatabaseDto dto = new EntregaDatabaseDto(1L, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.save(any(EntregaEntity.class)))
                .thenThrow(new org.springframework.dao.DataIntegrityViolationException("Duplicate entry"));

        // Act & Assert
        PersistedDatabaseException exception = assertThrows(PersistedDatabaseException.class, () -> {
            entregaRepositoryPort.save(dto);
        });

        assertEquals("Duplicate entry", exception.getMessage());
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        Long id = 1L;
        EntregaDatabaseDto dto = new EntregaDatabaseDto(id, 123L, 90.0, 45.0, "12345-678", now, null);
        EntregaEntity entity = new EntregaEntity(id, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(entregaRepository.save(any(EntregaEntity.class))).thenReturn(entity);

        // Act
        EntregaDatabaseDto result = entregaRepositoryPort.update(id, dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getIdPedido(), result.getIdPedido());
        assertEquals(dto.getLatitude(), result.getLatitude());
        assertEquals(dto.getLongitude(), result.getLongitude());
        assertEquals(dto.getCep(), result.getCep());
    }

    @Test
    public void testUpdate_NotFound() {
        // Arrange
        Long id = 1L;
        EntregaDatabaseDto dto = new EntregaDatabaseDto(id, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundDatabaseException exception = assertThrows(ResourceNotFoundDatabaseException.class, () -> {
            entregaRepositoryPort.update(id, dto);
        });

        assertEquals("Entrega not found: 1", exception.getMessage());
    }

    @Test
    public void testDelete_Success() {
        // Arrange
        Long id = 1L;
        EntregaEntity entity = new EntregaEntity(id, 123L, 45.0, 90.0, "12345-678", now, null);

        when(entregaRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        entregaRepositoryPort.delete(id);

        // Assert
        verify(entregaRepository, times(1)).delete(entity);
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        Long id = 1L;
        when(entregaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundDatabaseException exception = assertThrows(ResourceNotFoundDatabaseException.class, () -> {
            entregaRepositoryPort.delete(id);
        });

        assertEquals("Entrega not found: 1", exception.getMessage());
    }

    @Test
    public void testFindByCep_Success() {
        // Arrange
        String cep = "12345-678";
        EntregaEntity entity = new EntregaEntity(1L, 123L, 45.0, 90.0, cep, now, null);
        List<EntregaEntity> entities = List.of(entity);

        when(entregaRepository.findByCep(cep)).thenReturn(entities);

        // Act
        List<EntregaDatabaseDto> result = entregaRepositoryPort.findByCep(cep);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cep, result.get(0).getCep());
    }

    @Test
    public void testFindByCep_NotFound() {
        // Arrange
        String cep = "12345-678";
        when(entregaRepository.findByCep(cep)).thenReturn(List.of());

        // Act
        List<EntregaDatabaseDto> result = entregaRepositoryPort.findByCep(cep);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAll_Success() {
        // Arrange
        EntregaEntity entity1 = new EntregaEntity(1L, 123L, 45.0, 90.0, "12345-678", now, null);
        EntregaEntity entity2 = new EntregaEntity(2L, 124L, 46.0, 91.0, "98765-432", now, null);

        List<EntregaEntity> entregaEntities = Arrays.asList(entity1, entity2);

        when(entregaRepository.findAll()).thenReturn(entregaEntities);

        // Act
        List<EntregaDatabaseDto> result = entregaRepositoryPort.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(entity1.getIdPedido(), result.get(0).getIdPedido());
        assertEquals(entity2.getIdPedido(), result.get(1).getIdPedido());
        assertEquals(entity1.getLatitude(), result.get(0).getLatitude());
        assertEquals(entity2.getLatitude(), result.get(1).getLatitude());
        assertEquals(entity1.getLongitude(), result.get(0).getLongitude());
        assertEquals(entity2.getLongitude(), result.get(1).getLongitude());
        assertEquals(entity1.getCep(), result.get(0).getCep());
        assertEquals(entity2.getCep(), result.get(1).getCep());
    }

}
