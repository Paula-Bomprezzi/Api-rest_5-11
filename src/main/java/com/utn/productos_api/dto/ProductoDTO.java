package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import jakarta.validation.constraints.*;

// DTO para creación/actualización
public record ProductoDTO(
        @NotNull
        @NotBlank
        @Size(min = 3, max = 100)
        String nombre,

        @Size(max = 500)
        String descripcion,

        @NotNull
        @DecimalMin("0.01")   // importante!!! el valor va como String
        Double precio,

        @NotNull
        @Min(0)
        Integer stock,

        @NotNull
        Categoria categoria
) {}
