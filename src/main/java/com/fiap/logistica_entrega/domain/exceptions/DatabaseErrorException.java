package com.fiap.logistica_entrega.domain.exceptions;

import java.io.Serial;

public class DatabaseErrorException extends RuntimeException {
  @Serial private static final long serialVersionUID = 8293746510283746591L;

  public DatabaseErrorException(String message) {
    super(message);
  }
}
