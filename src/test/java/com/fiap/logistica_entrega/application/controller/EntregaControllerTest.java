package com.fiap.logistica_entrega.application.controller;

import com.fiap.logistica_entrega.application.controller.EntregaController;
import com.fiap.logistica_entrega.application.controller.inbound.InboundEntrega;
import com.fiap.logistica_entrega.application.controller.outbound.OutboundEntrega;
import com.fiap.logistica_entrega.domain.ports.inbound.CreateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.DeleteEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.FindEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.UpdateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EntregaControllerTest {

    @InjectMocks
    private EntregaController entregaController;

    @Mock
    private CreateEntregaUseCase createEntregaUseCase;

    @Mock
    private FindEntregaUseCase findEntregaUseCase;

    @Mock
    private UpdateEntregaUseCase updateEntregaUseCase;

    @Mock
    private DeleteEntregaUseCase entregaDeleteUseCase;

    private EntregaDto entregaDto;
    private InboundEntrega inboundEntrega;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        entregaDto = EntregaDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        inboundEntrega = InboundEntrega.builder()
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .build();
    }

    @Test
    void testFindAll() {
        when(findEntregaUseCase.findAll()).thenReturn(Collections.singletonList(entregaDto));

        ResponseEntity<List<OutboundEntrega>> response = entregaController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(findEntregaUseCase, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(findEntregaUseCase.findById(1L)).thenReturn(entregaDto);

        ResponseEntity<OutboundEntrega> response = entregaController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getIdEntrega());
        verify(findEntregaUseCase, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(createEntregaUseCase.create(any())).thenReturn(entregaDto);

        ResponseEntity<OutboundEntrega> response = entregaController.save(inboundEntrega);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getIdEntrega());
        verify(createEntregaUseCase, times(1)).create(any());
    }

    @Test
    void testUpdate() {
        when(updateEntregaUseCase.update(eq(1L), any())).thenReturn(entregaDto);

        ResponseEntity<OutboundEntrega> response = entregaController.update(1L, inboundEntrega);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getIdEntrega());
        verify(updateEntregaUseCase, times(1)).update(eq(1L), any());
    }

    @Test
    void testDelete() {
        ResponseEntity<OutboundEntrega> response = entregaController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(entregaDeleteUseCase, times(1)).delete(1L);
    }

    @Test
    void testSearchByCep() {
        when(findEntregaUseCase.findByCep("01000-000")).thenReturn(Collections.singletonList(entregaDto));

        ResponseEntity<List<OutboundEntrega>> response = entregaController.searchByCep("01000-000");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1L, response.getBody().get(0).getIdEntrega());
        verify(findEntregaUseCase, times(1)).findByCep("01000-000");
    }
}
