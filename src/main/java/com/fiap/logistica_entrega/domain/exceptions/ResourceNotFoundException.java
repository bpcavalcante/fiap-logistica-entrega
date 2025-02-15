package com.fiap.logistica_entrega.domain.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
  @Serial private static final long serialVersionUID = -9131235283731123025L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
