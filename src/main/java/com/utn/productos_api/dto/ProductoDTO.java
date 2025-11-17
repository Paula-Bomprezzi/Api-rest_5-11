package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

// DTO para creación/actualización
@Schema(description = "DTO para crear o actualizar un producto")
public record ProductoDTO(
        @Schema(description = "Nombre del producto", example = "Laptop HP", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @NotBlank
        @Size(min = 3, max = 100)
        String nombre,

        @Schema(description = "Descripción detallada del producto", example = "Laptop HP con procesador Intel i7, 16GB RAM")
        @Size(max = 500)
        String descripcion,

        @Schema(description = "Precio del producto en pesos", example = "125000.50", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @DecimalMin("0.01")   // importante!!! el valor va como String
        Double precio,

        @Schema(description = "Cantidad disponible en stock", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Min(0)
        Integer stock,

        @Schema(description = "Categoría del producto", example = "ELECTRONICA", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Categoria categoria
) {}
