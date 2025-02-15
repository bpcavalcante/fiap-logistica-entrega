package com.fiap.logistica_entrega.application.controller.outbound.converter;

import com.fiap.logistica_entrega.application.controller.outbound.OutboundEntrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;

public class OutboundEntregaConverter {
  private OutboundEntregaConverter() {}

  public static OutboundEntregaConverter getInstance() {
    return SingletonHelper.CLIENT_CONVERTER_SINGLETON;
  }

  public OutboundEntrega toOutbound(EntregaDto entregaDto) {
    return OutboundEntrega.builder()
        .idEntrega(entregaDto.getIdEntrega())
        .idPedido(entregaDto.getIdPedido())
        .latitude(entregaDto.getLatitude())
        .longitude(entregaDto.getLongitude())
        .cep(entregaDto.getCep())
        .dateCreated(entregaDto.getDateCreated())
        .dateLastUpdated(entregaDto.getDateLastUpdated())
        .build();
  }

  // Bill Pugh Singleton
  private static class SingletonHelper {
    private static final OutboundEntregaConverter CLIENT_CONVERTER_SINGLETON =
        new OutboundEntregaConverter();
  }
}
