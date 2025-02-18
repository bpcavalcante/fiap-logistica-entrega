package com.fiap.logistica_entrega.infrastructure.converter;

import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntregaEntityConverterTest {

    private EntregaEntityConverter converter;

    @BeforeEach
    void setUp() {
        converter = EntregaEntityConverter.getInstance();
    }

    @Test
    void testToDatabaseDto_Conversion() {
        EntregaEntity entregaEntity = EntregaEntity.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        EntregaDatabaseDto entregaDatabaseDto = converter.toDatabaseDto(entregaEntity);

        assertNotNull(entregaDatabaseDto, "EntregaDatabaseDto não deve ser nulo");
        assertEquals(1L, entregaDatabaseDto.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entregaDatabaseDto.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, entregaDatabaseDto.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entregaDatabaseDto.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaDatabaseDto.getCep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToEntity_Conversion() {
        EntregaDatabaseDto entregaDatabaseDto = EntregaDatabaseDto.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        EntregaEntity entregaEntity = converter.toEntity(entregaDatabaseDto);

        assertNotNull(entregaEntity, "EntregaEntity não deve ser nulo");
        assertEquals(1L, entregaEntity.getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(100L, entregaEntity.getIdPedido(), "ID de pedido deve ser 100");
        assertEquals(23.5505, entregaEntity.getLatitude(), 0.0001, "Latitude deve ser 23.5505");
        assertEquals(-46.6333, entregaEntity.getLongitude(), 0.0001, "Longitude deve ser -46.6333");
        assertEquals("01000-000", entregaEntity.getCep(), "CEP deve ser 01000-000");
    }

    @Test
    void testToDatabaseListDto_Conversion() {
        EntregaEntity entregaEntity1 = EntregaEntity.builder()
                .idEntrega(1L)
                .idPedido(100L)
                .latitude(23.5505)
                .longitude(-46.6333)
                .cep("01000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        EntregaEntity entregaEntity2 = EntregaEntity.builder()
                .idEntrega(2L)
                .idPedido(200L)
                .latitude(40.7128)
                .longitude(-74.0060)
                .cep("02000-000")
                .dateCreated(Instant.now())
                .dateLastUpdated(Instant.now())
                .build();

        List<EntregaEntity> entregaEntities = List.of(entregaEntity1, entregaEntity2);

        List<EntregaDatabaseDto> entregaDatabaseDtos = converter.toDatabaseListDto(entregaEntities);

        assertNotNull(entregaDatabaseDtos, "Lista de EntregaDatabaseDto não deve ser nula");
        assertEquals(2, entregaDatabaseDtos.size(), "A lista deve conter 2 elementos");

        assertEquals(1L, entregaDatabaseDtos.get(0).getIdEntrega(), "ID de entrega deve ser 1");
        assertEquals(2L, entregaDatabaseDtos.get(1).getIdEntrega(), "ID de entrega deve ser 2");
    }
}
