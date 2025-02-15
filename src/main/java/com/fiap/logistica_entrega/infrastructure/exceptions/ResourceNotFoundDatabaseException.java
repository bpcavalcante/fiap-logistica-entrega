package com.fiap.logistica_entrega.infrastructure.exceptions;

import java.io.Serial;

public class ResourceNotFoundDatabaseException extends RuntimeException {
  @Serial private static final long serialVersionUID = -9131235283731123025L;

  public ResourceNotFoundDatabaseException(String message) {
    super(message);
  }
}
