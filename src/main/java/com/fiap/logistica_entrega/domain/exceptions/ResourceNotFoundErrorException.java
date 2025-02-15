package com.fiap.logistica_entrega.domain.exceptions;

import java.io.Serial;

public class ResourceNotFoundErrorException extends RuntimeException {
  @Serial private static final long serialVersionUID = 8293746510283746591L;

  public ResourceNotFoundErrorException(String message) {
    super(message);
  }
}
