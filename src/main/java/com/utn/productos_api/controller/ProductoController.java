package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //ENDPOINTS
    @GetMapping
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoService.obtenerTodos()
                .stream()
                .map(productoService::convertirAResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(productoService::convertirAResponseDto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(productoService::convertirAResponseDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoDTO dto){
        //Este se lee de abajo para arriba (o de adentro hacia afuera)
        ProductoResponseDTO productoGuardado = productoService.convertirAResponseDto(
                productoService.crearProducto(
                        productoService.convertirAEntidad(dto)
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO editar(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto){
        return productoService.convertirAResponseDto(productoService.actualizarProducto(id, productoService.convertirAEntidad(dto))
        );
    }

    @PatchMapping("/{id}/stock")
    public ProductoResponseDTO actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO dto){
        return productoService.convertirAResponseDto(
                productoService.actualizarStock(id, dto.stock())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
