package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteEntregaTest {

    @Mock
    private EntregaRepositoryPort entregaRepositoryPort;

    @InjectMocks
    private DeleteEntrega deleteEntrega;

    private long validId;
    private long invalidId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validId = 1L;
        invalidId = 999L;
    }

    @Test
    public void testDeleteEntregaSuccess() {
        doNothing().when(entregaRepositoryPort).delete(validId);

        deleteEntrega.delete(validId);

        verify(entregaRepositoryPort).delete(validId);
    }

    @Test
    public void testDeleteEntregaFailureDueToNotFound() {
        doThrow(new ResourceNotFoundDatabaseException("Entrega not found")).when(entregaRepositoryPort).delete(invalidId);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteEntrega.delete(invalidId);
        });

        assertEquals("Entrega not found", exception.getMessage());

        verify(entregaRepositoryPort).delete(invalidId);
    }
}
