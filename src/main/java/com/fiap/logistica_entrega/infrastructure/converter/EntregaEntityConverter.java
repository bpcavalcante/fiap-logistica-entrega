package com.fiap.logistica_entrega.infrastructure.converter;

import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.infrastructure.entities.EntregaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EntregaEntityConverter {
  private EntregaEntityConverter() {}

  public static EntregaEntityConverter getInstance() {
    return SingletonHelper.CLIENT_CONVERTER_SINGLETON;
  }

  public EntregaDatabaseDto toDatabaseDto(EntregaEntity entrega) {
    return EntregaDatabaseDto.builder()
        .idEntrega(entrega.getIdEntrega())
        .idPedido(entrega.getIdPedido())
        .longitude(entrega.getLongitude())
        .latitude(entrega.getLatitude())
        .cep(entrega.getCep())
        .dateCreated(entrega.getDateCreated())
        .dateLastUpdated(entrega.getDateLastUpdated())
        .build();
  }

  public EntregaEntity toEntity(EntregaDatabaseDto entrega) {
    return EntregaEntity.builder()
        .idEntrega(entrega.getIdEntrega())
        .idPedido(entrega.getIdPedido())
        .longitude(entrega.getLongitude())
        .latitude(entrega.getLatitude())
        .cep(entrega.getCep())
        .dateCreated(entrega.getDateCreated())
        .dateLastUpdated(entrega.getDateLastUpdated())
        .build();
  }

  public List<EntregaDatabaseDto> toDatabaseListDto(List<EntregaEntity> entregas) {
    return entregas.stream()
            .map(entrega -> EntregaDatabaseDto.builder()
                    .idEntrega(entrega.getIdEntrega())
                    .idPedido(entrega.getIdPedido())
                    .longitude(entrega.getLongitude())
                    .latitude(entrega.getLatitude())
                    .cep(entrega.getCep())
                    .dateCreated(entrega.getDateCreated())
                    .dateLastUpdated(entrega.getDateLastUpdated())
                    .build())
            .collect(Collectors.toList());
  }

  // Bill Pugh Singleton
  private static class SingletonHelper {
    private static final EntregaEntityConverter CLIENT_CONVERTER_SINGLETON =
        new EntregaEntityConverter();
  }
}
