package com.fiap.logistica_entrega.application.controller.inbound.converter;

import com.fiap.logistica_entrega.application.controller.inbound.InboundEntrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;

public class InboundEntregaConverter {
  private InboundEntregaConverter() {}

  public static InboundEntregaConverter getInstance() {
    return SingletonHelper.CLIENT_CONVERTER_SINGLETON;
  }

  public EntregaDto toDTO(InboundEntrega inboundEntrega) {
    return EntregaDto.builder()
        .idPedido(inboundEntrega.getIdPedido())
        .latitude(inboundEntrega.getLatitude())
        .longitude(inboundEntrega.getLongitude())
        .cep(inboundEntrega.getCep())
        .build();
  }

  // Bill Pugh Singleton
  private static class SingletonHelper {
    private static final InboundEntregaConverter CLIENT_CONVERTER_SINGLETON =
        new InboundEntregaConverter();
  }
}
