package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.Entrega;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundErrorException;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import com.fiap.logistica_entrega.domain.ports.inbound.converter.EntregaConverterInbound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateEntregaTest {

    @Mock
    private EntregaRepositoryPort entregaRepositoryPort;

    @Mock
    private EntregaConverterInbound entregaConverter;

    @InjectMocks
    private CreateEntrega createEntrega;

    private EntregaDto entregaDto;
    private Entrega entrega;
    private EntregaDatabaseDto entregaDatabaseDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        var now = Instant.now();

        entregaDto = new EntregaDto(1L, 1L, 40.7128, -74.0060, "10001-000", now, null);
        entrega = new Entrega(1L, 1L, 40.7128, -74.0060, "10001-000", now, null);
        entregaDatabaseDto = new EntregaDatabaseDto(1L, 1L, 40.7128, -74.0060, "10001-000",now, null);
    }

    @Test
    public void testCreateEntregaSuccess() {
        when(entregaConverter.toDomain(entregaDto)).thenReturn(entrega);
        when(entregaConverter.toDatabaseDTO(entrega)).thenReturn(entregaDatabaseDto);
        when(entregaRepositoryPort.save(entregaDatabaseDto)).thenReturn(entregaDatabaseDto);
        when(entregaConverter.toEntregaDto(entregaDatabaseDto)).thenReturn(entregaDto);

        EntregaDto result = createEntrega.create(entregaDto);

        assertNotNull(result);
        assertEquals(entregaDto.getIdEntrega(), result.getIdEntrega());
        assertEquals(entregaDto.getIdPedido(), result.getIdPedido());

        verify(entregaRepositoryPort).save(entregaDatabaseDto);
    }

    @Test
    public void testCreateEntregaFailureDueToRepositoryError() {
        when(entregaConverter.toDomain(entregaDto)).thenReturn(entrega);
        when(entregaConverter.toDatabaseDTO(entrega)).thenReturn(entregaDatabaseDto);
        when(entregaRepositoryPort.save(entregaDatabaseDto)).thenThrow(new ResourceNotFoundDatabaseException("Erro ao salvar"));

        ResourceNotFoundErrorException exception = assertThrows(ResourceNotFoundErrorException.class, () -> {
            createEntrega.create(entregaDto);
        });

        assertEquals("Erro ao salvar", exception.getMessage());

        verify(entregaRepositoryPort).save(entregaDatabaseDto);
    }
}
