package com.fiap.logistica_entrega.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class EntregaTest {

    @Test
    public void testValidEntrega() {
        Instant now = Instant.now();

        Entrega entrega = new Entrega(1L, 2L, 30.0, 60.0, "12345-678", now, now);

        assertNotNull(entrega);
        assertEquals(1L, entrega.idEntrega());
        assertEquals(2L, entrega.idPedido());
        assertEquals(30.0, entrega.longitude());
        assertEquals(60.0, entrega.latitude());
        assertEquals("12345-678", entrega.cep());
        assertEquals(now, entrega.dateCreated());
        assertEquals(now, entrega.dateLastUpdated());
    }

    @Test
    public void testIdPedidoCannotBeNull() {
        Instant now = Instant.now();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Entrega(null, null, 30.0, 60.0, "12345-678", now, now);
        });

        assertEquals("IdPedido cannot be null", exception.getMessage());
    }

    @Test
    public void testInvalidLongitude() {
        Instant now = Instant.now();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Entrega(1L, 2L, 200.0, 60.0, "12345-678", now, now);
        });

        assertEquals("Longitude is invalid or null: 200.0", exception.getMessage());
    }

    @Test
    public void testInvalidLatitude() {
        Instant now = Instant.now();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Entrega(1L, 2L, 30.0, 100.0, "12345-678", now, now);
        });

        assertEquals("Latitude is invalid or null: 100.0", exception.getMessage());
    }

    @Test
    public void testInvalidCep() {
        Instant now = Instant.now();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Entrega(1L, 2L, 30.0, 60.0, "1234-567", now, now); // CEP inválido, deve ser 5 dígitos-3 dígitos
        });

        assertEquals("CEP is invalid or null: 1234-567", exception.getMessage());
    }

    @Test
    public void testNullCep() {
        Instant now = Instant.now();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Entrega(1L, 2L, 30.0, 60.0, null, now, now);
        });

        assertEquals("CEP is invalid or null: null", exception.getMessage());
    }
}
