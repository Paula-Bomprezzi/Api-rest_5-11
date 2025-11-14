package com.utn.productos_api.dto;
import java.time.LocalDateTime;


//Estos DTO los crea GolbalExceptionHandler
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        String path
) {
    // Constructor adicional para inicializar timestamp automáticamente
    public ErrorResponse(int status, String message, String path) {
        this(LocalDateTime.now(), status, message, path);
    }
}
