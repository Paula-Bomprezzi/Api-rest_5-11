package com.utn.productos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

//Estos DTO los crea GolbalExceptionHandler
@Schema(description = "DTO para respuestas de error estructuradas")
public record ErrorResponse(
        @Schema(description = "Fecha y hora en que ocurrió el error", example = "2024-01-15T10:30:00")
        LocalDateTime timestamp,
        
        @Schema(description = "Código de estado HTTP", example = "404")
        int status,
        
        @Schema(description = "Mensaje descriptivo del error", example = "Producto no encontrado con id: 999")
        String message,
        
        @Schema(description = "Ruta de la petición que generó el error", example = "/api/productos/999")
        String path
) {
    // Constructor adicional para inicializar timestamp automáticamente
    public ErrorResponse(int status, String message, String path) {
        this(LocalDateTime.now(), status, message, path);
    }
}
