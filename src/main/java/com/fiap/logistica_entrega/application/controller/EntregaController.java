package com.fiap.logistica_entrega.application.controller;

import com.fiap.logistica_entrega.application.controller.inbound.InboundEntrega;
import com.fiap.logistica_entrega.application.controller.inbound.converter.InboundEntregaConverter;
import com.fiap.logistica_entrega.application.controller.outbound.OutboundEntrega;
import com.fiap.logistica_entrega.application.controller.outbound.converter.OutboundEntregaConverter;
import com.fiap.logistica_entrega.domain.ports.inbound.CreateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.DeleteEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.FindEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.UpdateEntregaUseCase;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
@RequiredArgsConstructor
public class EntregaController {

  private final CreateEntregaUseCase createEntregaUseCase;
  private final FindEntregaUseCase findEntregaUseCase;
  private final UpdateEntregaUseCase updateEntregaUseCase;
  private final DeleteEntregaUseCase entregaDeleteUseCase;

  @GetMapping
  public ResponseEntity<List<OutboundEntrega>> findAll() {
    List<EntregaDto> entregas = findEntregaUseCase.findAll();
    return ResponseEntity.ok(
            OutboundEntregaConverter.getInstance().toListOutbound(entregas));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OutboundEntrega> findById(@PathVariable Long id) {
    EntregaDto entrega = findEntregaUseCase.findById(id);
    return ResponseEntity.ok(OutboundEntregaConverter.getInstance().toOutbound(entrega));
  }

  @GetMapping("/cep/{cep}")
  public ResponseEntity<List<OutboundEntrega>> searchByCep(
      @PathVariable String cep) {
    List<EntregaDto> entregas = findEntregaUseCase.findByCep(cep);
    return ResponseEntity.ok(
        OutboundEntregaConverter.getInstance().toListOutbound(entregas));
  }

  @PostMapping
  public ResponseEntity<OutboundEntrega> save(@RequestBody InboundEntrega inboundEntrega) {
    EntregaDto createdEntrega =
        createEntregaUseCase.create(InboundEntregaConverter.getInstance().toDTO(inboundEntrega));

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(OutboundEntregaConverter.getInstance().toOutbound(createdEntrega));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OutboundEntrega> update(
      @PathVariable Long id, @RequestBody InboundEntrega editInboundEntrega) {
    EntregaDto editedEntrega =
        updateEntregaUseCase.update(
            id, InboundEntregaConverter.getInstance().toDTO(editInboundEntrega));
    return ResponseEntity.ok(OutboundEntregaConverter.getInstance().toOutbound(editedEntrega));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<OutboundEntrega> delete(@PathVariable Long id) {
    entregaDeleteUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }
}
