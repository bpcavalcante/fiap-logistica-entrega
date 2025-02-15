package com.fiap.logistica_entrega.domain;

import java.time.Instant;
import java.util.Optional;

public record Entrega(
    Long idEntrega,
    Long idPedido,
    Double longitude,
    Double latitude,
    String cep,
    Instant dateCreated,
    Instant dateLastUpdated
) {
  public Entrega {
    if (idPedido == null) {
      throw new IllegalArgumentException("IdPedido cannot be null");
    }

    Optional.ofNullable(longitude)
        .filter(l -> l >= -180 && l <= 180)
        .orElseThrow(() -> new IllegalArgumentException("Longitude is invalid or null: " + longitude));

    Optional.ofNullable(latitude)
        .filter(l -> l >= -90 && l <= 90)
        .orElseThrow(() -> new IllegalArgumentException("Latitude is invalid or null: " + latitude));

    Optional.ofNullable(cep)
        .filter(c -> !c.isBlank())
        .filter(c -> c.matches("\\d{5}-?\\d{3}"))
        .orElseThrow(() -> new IllegalArgumentException("CEP is invalid or null: " + cep));
  }
}
