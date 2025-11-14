package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;

// DTO para respuestas (solo lectura)
public record ProductoResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Categoria categoria
) {}
