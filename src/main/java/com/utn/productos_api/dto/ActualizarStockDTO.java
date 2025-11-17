package com.utn.productos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para actualizar únicamente el stock de un producto")
public record ActualizarStockDTO(
        @Schema(description = "Nueva cantidad de stock (debe ser mayor o igual a 0)", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Min(0)
        Integer stock
) {
}
