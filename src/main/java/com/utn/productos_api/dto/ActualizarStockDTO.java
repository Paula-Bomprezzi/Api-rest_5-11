package com.utn.productos_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ActualizarStockDTO(
        @NotNull
        @Min(0)
        Integer stock
) {
}
