package com.fiap.logistica_entrega.application.controller.outbound.converter;

import com.fiap.logistica_entrega.application.controller.outbound.OutboundEntrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutboundEntregaConverterTest {

    private OutboundEntregaConverter converter;

    @BeforeEach
    void setUp() {
        // Inicializa o conversor
        converter = OutboundEntregaConverter.getInstance();
    }

    @Test
    void testToOutbound_Conversion() {
        // Criando um exemplo de EntregaDto
        EntregaDto entregaDto = EntregaDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        OutboundEntrega outboundEntrega = converter.toOutbound(entregaDto);

        assertNotNull(outboundEntrega, "OutboundEntrega não deve ser nulo");
        assertEquals(1L, outboundEntrega.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, outboundEntrega.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, outboundEntrega.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, outboundEntrega.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", outboundEntrega.getCep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToListOutbound_Conversion() {
        List<EntregaDto> entregasDto = Arrays.asList(
                EntregaDto.builder()
                        .idEntrega(1L)
                        .idPedido(100L)
                        .latitude(23.5505)
                        .longitude(-46.6333)
                        .cep("01000-000")
                        .dateCreated(Instant.now())
                        .dateLastUpdated(Instant.now())
                        .build(),
                EntregaDto.builder()
                        .idEntrega(2L)
                        .idPedido(200L)
                        .latitude(40.7128)
                        .longitude(-74.0060)
                        .cep("10001-000")
                        .dateCreated(Instant.now())
                        .dateLastUpdated(Instant.now())
                        .build()
        );

        List<OutboundEntrega> outboundEntregas = converter.toListOutbound(entregasDto);

        assertNotNull(outboundEntregas, "Lista de OutboundEntrega não deve ser nula");
        assertEquals(2, outboundEntregas.size(), "A lista deve ter 2 elementos");

        assertEquals(1L, outboundEntregas.get(0).getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, outboundEntregas.get(0).getIdPedido(), "ID de pedido deve ser 100");

        assertEquals(2L, outboundEntregas.get(1).getIdEntrega(), "ID de entrega deve ser 2");
        assertEquals(200L, outboundEntregas.get(1).getIdPedido(), "ID de pedido deve ser 200");
    }
}
