package com.utn.productos_api.exception;

//cuando se intenta reducir stock por debajo de 0
public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
