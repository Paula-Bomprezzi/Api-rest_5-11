package com.utn.productos_api.service;

import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.respository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    public ProductoRepository productoRepository;
    //Librería para conversión de DTO
    private final MapperService mapperService;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, MapperService mapperService) {
        this.productoRepository = productoRepository;
        this.mapperService = mapperService;
    }



    //CONVERSIONES DE DTO <--> POJO
    public ProductoDTO convertirADto(Producto producto) {
        return mapperService.convertir(producto, ProductoDTO.class);
    }

    public Producto convertirAEntidad(ProductoDTO dto) {
        return mapperService.convertir(dto, Producto.class);
    }

    public ProductoResponseDTO convertirAResponseDto(Producto producto) {
        return mapperService.convertir(producto, ProductoResponseDTO.class);
    }



    public Producto crearProducto(Producto producto) {
        Producto guardado = productoRepository.save(producto);
        System.out.println("PRODUCTO GUARDADO CON ÉXITO");
        return guardado;
    }

public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
}

public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
}
public List<Producto> obtenerPorCategoria(Categoria categoria){
        return productoRepository.findByCategoria(categoria);
}

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return obtenerPorId(id)
                .map(objetoProducto -> {
                    productoActualizado.setId(id);
                    Producto guardado = productoRepository.save(productoActualizado);
                    System.out.println("Producto " + guardado.getNombre() + " actualizado exitosamente");
                    return guardado;
                })
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id " + id));
    }

public Producto actualizarStock(Long id, Integer nuevoStock){
        return obtenerPorId(id)
                .map(objetoEncontrado -> {
                    Integer stockViejo = objetoEncontrado.getStock();
                    objetoEncontrado.setStock(nuevoStock);
                    Producto guardado = productoRepository.save(objetoEncontrado);
                    System.out.println("Producto " + guardado.getNombre() +" con stock Actualizado exitosamente"
                            +"\n"+"Stock anterior: " + stockViejo
                            +"\n"+"Stock actualizado: " + guardado.getStock() );
                    return guardado;
                })
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con id " + id));
}

public void eliminarProducto(Long id){
    obtenerPorId(id).ifPresentOrElse(
      objetoEncontrado ->{
          productoRepository.deleteById(id);
          System.out.println("El producto se ha eliminado correctamente");
      },
            ()->{
                throw  new RuntimeException("No se encontro el producto con id " + id);
            }
    );
};


}
