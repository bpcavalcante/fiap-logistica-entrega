package com.fiap.logistica_entrega.infrastructure.exceptions;

import java.io.Serial;

public class PersistedDatabaseException extends RuntimeException {
  @Serial private static final long serialVersionUID = 7181622071068291648L;

  public PersistedDatabaseException(String message) {
    super(message);
  }
}
