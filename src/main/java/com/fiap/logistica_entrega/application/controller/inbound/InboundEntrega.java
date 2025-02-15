package com.fiap.logistica_entrega.application.controller.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundEntrega {
  private Long idPedido;
  private Double longitude;
  private Double latitude;
  private String cep;
}
