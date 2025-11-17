package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ErrorResponse;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //ENDPOINTS
    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista con todos los productos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoResponseDTO.class))))
    })
    @GetMapping
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoService.obtenerTodos()
                .stream()
                .map(productoService::convertirAResponseDto)
                .toList();
    }

    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(productoService::convertirAResponseDto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Operation(summary = "Obtener productos por categoría", description = "Retorna una lista de productos filtrados por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoResponseDTO.class))))
    })
    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(productoService::convertirAResponseDto)
                .toList();
    }

    @Operation(summary = "Crear nuevo producto", description = "Crea un nuevo producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos del producto",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
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

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos del producto",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ProductoResponseDTO editar(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto){
        return productoService.convertirAResponseDto(productoService.actualizarProducto(id, productoService.convertirAEntidad(dto))
        );
    }

    @Operation(summary = "Actualizar stock de producto", description = "Actualiza únicamente el stock de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación: stock negativo o producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/stock")
    public ProductoResponseDTO actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO dto){
        return productoService.convertirAResponseDto(
                productoService.actualizarStock(id, dto.stock())
        );
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }



}
