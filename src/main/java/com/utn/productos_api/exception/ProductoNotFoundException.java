package com.utn.productos_api.exception;

//cuando no se encuentra un producto por ID
public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
