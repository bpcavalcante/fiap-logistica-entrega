package com.fiap.logistica_entrega.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entrega")
public class EntregaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_entrega", nullable = false, updatable = false)
  private Long idEntrega;

  @Column(name = "id_pedido", nullable = false)
  private Long idPedido;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Column(name = "cep", nullable = false, length = 10)
  private String cep;

  @Column(name = "date_created", nullable = false)
  private Instant dateCreated;

  @Column(name = "date_last_updated")
  private Instant dateLastUpdated;

  @PrePersist
  protected void onCreate() {
    this.dateCreated = Instant.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.dateLastUpdated = Instant.now();
  }
}
