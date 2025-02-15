package com.fiap.logistica_entrega.domain.ports.outbound.converter;

import com.fiap.logistica_entrega.domain.Entrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;

public class EntregaConverterDatabase {
  private EntregaConverterDatabase() {}

  public static EntregaConverterDatabase getInstance() {
    return SingletonHelper.CLIENT_CONVERTER_SINGLETON;
  }

  public EntregaDto toEntregaDto(EntregaDatabaseDto entregaDatabaseDto) {
    return EntregaDto.builder()
        .idEntrega(entregaDatabaseDto.getIdEntrega())
        .idPedido(entregaDatabaseDto.getIdPedido())
        .longitude(entregaDatabaseDto.getLongitude())
        .latitude(entregaDatabaseDto.getLatitude())
        .cep(entregaDatabaseDto.getCep())
        .dateCreated(entregaDatabaseDto.getDateCreated())
        .dateLastUpdated(entregaDatabaseDto.getDateLastUpdated())
        .build();
  }

  public EntregaDatabaseDto toDatabaseDTO(Entrega entrega) {
    return EntregaDatabaseDto.builder()
        .idEntrega(entrega.idEntrega())
        .idPedido(entrega.idPedido())
        .longitude(entrega.longitude())
        .latitude(entrega.latitude())
        .cep(entrega.cep())
        .dateCreated(entrega.dateCreated())
        .dateLastUpdated(entrega.dateLastUpdated())
        .build();
  }

  // Bill Pugh Singleton
  private static class SingletonHelper {
    private static final EntregaConverterDatabase CLIENT_CONVERTER_SINGLETON =
        new EntregaConverterDatabase();
  }
}
