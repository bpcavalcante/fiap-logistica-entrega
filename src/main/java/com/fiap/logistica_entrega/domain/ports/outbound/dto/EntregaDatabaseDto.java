package com.fiap.logistica_entrega.domain.ports.outbound.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EntregaDatabaseDto {
  private Long idEntrega;
  private Long idPedido;
  private Double longitude;
  private Double latitude;
  private String cep;
  private Instant dateCreated;
  private Instant dateLastUpdated;
}
