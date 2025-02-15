package com.fiap.logistica_entrega.domain.ports.inbound.converter;

import com.fiap.logistica_entrega.domain.Entrega;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;

public class EntregaConverterInbound {
  private EntregaConverterInbound() {}

  public static EntregaConverterInbound getInstance() {
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

  public Entrega toDomain(EntregaDto entregaDto) {
    return new Entrega(
        entregaDto.getIdEntrega(),
        entregaDto.getIdPedido(),
        entregaDto.getLongitude(),
        entregaDto.getLatitude(),
        entregaDto.getCep(),
        entregaDto.getDateCreated(),
        entregaDto.getDateLastUpdated());
  }

  public Entrega toDomain(EntregaDatabaseDto entregaDatabaseDto) {
    return new Entrega(
        entregaDatabaseDto.getIdEntrega(),
        entregaDatabaseDto.getIdPedido(),
        entregaDatabaseDto.getLongitude(),
        entregaDatabaseDto.getLatitude(),
        entregaDatabaseDto.getCep(),
        entregaDatabaseDto.getDateCreated(),
        entregaDatabaseDto.getDateLastUpdated()
    );
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

  public EntregaDto toDTO(Entrega entrega) {
    return EntregaDto.builder()
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
    private static final EntregaConverterInbound CLIENT_CONVERTER_SINGLETON =
        new EntregaConverterInbound();
  }
}
