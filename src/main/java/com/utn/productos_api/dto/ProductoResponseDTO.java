package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

// DTO para respuestas (solo lectura)
@Schema(description = "DTO de respuesta con la información completa del producto")
public record ProductoResponseDTO(
        @Schema(description = "ID único del producto", example = "1")
        Long id,
        
        @Schema(description = "Nombre del producto", example = "Laptop HP")
        String nombre,
        
        @Schema(description = "Descripción detallada del producto", example = "Laptop HP con procesador Intel i7, 16GB RAM")
        String descripcion,
        
        @Schema(description = "Precio del producto en pesos", example = "125000.50")
        Double precio,
        
        @Schema(description = "Cantidad disponible en stock", example = "50")
        Integer stock,
        
        @Schema(description = "Categoría del producto", example = "ELECTRONICA")
        Categoria categoria
) {}
