package com.fiap.logistica_entrega.application.controller.inbound.converter;
import com.fiap.logistica_entrega.application.controller.inbound.InboundEntrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InboundEntregaConverterTest {

    private InboundEntregaConverter converter;

    @BeforeEach
    void setUp() {
        converter = InboundEntregaConverter.getInstance();
    }

    @Test
    void testToDTO_Conversion() {
        InboundEntrega inboundEntrega = mock(InboundEntrega.class);

        when(inboundEntrega.getIdPedido()).thenReturn(100L);
        when(inboundEntrega.getLatitude()).thenReturn(23.5505);
        when(inboundEntrega.getLongitude()).thenReturn(-46.6333);
        when(inboundEntrega.getCep()).thenReturn("01000-000");

        EntregaDto entregaDto = converter.toDTO(inboundEntrega);

        assertNotNull(entregaDto, "EntregaDto não deve ser nulo");
        assertEquals(100L, entregaDto.getIdPedido(), "ID do pedido deve ser 100");
        assertEquals(23.5505, entregaDto.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entregaDto.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaDto.getCep(), "CEP deve ser 01000-000");

        verify(inboundEntrega, times(1)).getIdPedido();
        verify(inboundEntrega, times(1)).getLatitude();
        verify(inboundEntrega, times(1)).getLongitude();
        verify(inboundEntrega, times(1)).getCep();
    }
}
