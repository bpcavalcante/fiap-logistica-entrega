package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateEntregaTest {

    @Mock
    private EntregaRepositoryPort entregaRepositoryPort;

    @InjectMocks
    private UpdateEntrega updateEntrega;

    private EntregaDto entregaDto;
    private EntregaDatabaseDto entregaDatabaseDto;
    private long validId;
    private long invalidId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validId = 1L;
        invalidId = 99L;
        entregaDto = EntregaDto.builder()
                .idEntrega(validId)
                .idPedido(123L)
                .cep("12345-678")
                .longitude(-46.633308)
                .latitude(-46.633308)
                .build();
        entregaDatabaseDto = EntregaDatabaseDto.builder()
                .idEntrega(validId)
                .idPedido(123L)
                .cep("12345-678")
                .longitude(-46.633308)
                .latitude(-46.633308)
                .build();
    }

    @Test
    public void testUpdateSuccess() {
        when(entregaRepositoryPort.update(eq(validId), any(EntregaDatabaseDto.class))).thenReturn(entregaDatabaseDto);

        EntregaDto result = updateEntrega.update(validId, entregaDto);

        assertNotNull(result);
        assertEquals(validId, result.getIdEntrega());
        assertEquals("12345-678", result.getCep());

        verify(entregaRepositoryPort).update(eq(validId), any(EntregaDatabaseDto.class));
    }

    @Test
    public void testUpdateNotFound() {
        when(entregaRepositoryPort.update(eq(invalidId), any(EntregaDatabaseDto.class)))
                .thenThrow(new ResourceNotFoundDatabaseException("Entrega not found"));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> updateEntrega.update(invalidId, entregaDto));
        assertEquals("Entrega not found", exception.getMessage());

        verify(entregaRepositoryPort).update(eq(invalidId), any(EntregaDatabaseDto.class));
    }
}
