package com.fiap.logistica_entrega.domain.ports.inbound.converter;

import com.fiap.logistica_entrega.domain.Entrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EntregaConverterInboundTest {

    private EntregaConverterInbound converter;

    @BeforeEach
    void setUp() {
        converter = EntregaConverterInbound.getInstance();
    }

    @Test
    void testToEntregaDto_Conversion() {
        EntregaDatabaseDto entregaDatabaseDto = EntregaDatabaseDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        EntregaDto entregaDto = converter.toEntregaDto(entregaDatabaseDto);

        assertNotNull(entregaDto, "EntregaDto não deve ser nulo");
        assertEquals(1L, entregaDto.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entregaDto.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, entregaDto.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entregaDto.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaDto.getCep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToDomain_Conversion_From_EntregaDto() {
        EntregaDto entregaDto = EntregaDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        Entrega entrega = converter.toDomain(entregaDto);

        assertNotNull(entrega, "Entrega não deve ser nulo");
        assertEquals(1L, entrega.idEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entrega.idPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, entrega.latitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entrega.longitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entrega.cep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToDomain_Conversion_From_EntregaDatabaseDto() {
        EntregaDatabaseDto entregaDatabaseDto = EntregaDatabaseDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        Entrega entrega = converter.toDomain(entregaDatabaseDto);

        assertNotNull(entrega, "Entrega não deve ser nulo");
        assertEquals(1L, entrega.idEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entrega.idPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, entrega.latitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entrega.longitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entrega.cep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToDatabaseDTO_Conversion() {
        Entrega entrega = new Entrega(
                1L, 100L, 23.5505, -46.6333, "01000-000", Instant.now(), Instant.now()
        );

        EntregaDatabaseDto entregaDatabaseDto = converter.toDatabaseDTO(entrega);

        assertNotNull(entregaDatabaseDto, "EntregaDatabaseDto não deve ser nulo");
        assertEquals(1L, entregaDatabaseDto.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entregaDatabaseDto.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(-46.6333, entregaDatabaseDto.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(23.5505, entregaDatabaseDto.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaDatabaseDto.getCep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToDTO_Conversion() {
        Entrega entrega = new Entrega(
                1L, 100L, 23.5505, -46.6333, "01000-000", Instant.now(), Instant.now()
        );

        EntregaDto entregaDto = converter.toDTO(entrega);

        assertNotNull(entregaDto, "EntregaDto não deve ser nulo");
        assertEquals(1L, entregaDto.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entregaDto.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(-46.6333, entregaDto.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(23.5505, entregaDto.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaDto.getCep(), "CEP deve ser 01000-000");
    }
}
