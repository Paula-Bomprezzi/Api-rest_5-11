package com.utn.productos_api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Categorías disponibles para los productos")
public enum Categoria {
    ELECTRONICA,
    ROPA,
    ALIMENTOS,
    HOGAR,
    DEPORTES
}
