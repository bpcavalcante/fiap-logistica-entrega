package com.fiap.logistica_entrega.application.controller.outbound;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboundEntrega {
  private Long idEntrega;
  private Long idPedido;
  private Double longitude;
  private Double latitude;
  private String cep;
  private Instant dateCreated;
  private Instant dateLastUpdated;
}