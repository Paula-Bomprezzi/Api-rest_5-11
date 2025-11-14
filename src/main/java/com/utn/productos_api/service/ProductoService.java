package com.utn.productos_api.service;

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

    @Autowired
    public ProductoService(ProductoService productoService) {
    }

    public void crearProducto(Producto producto){
    productoRepository.save(producto);
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
public void actualizarProducto(Long id, Producto productoActualizado){
      obtenerPorId(id).ifPresentOrElse(
            objetoProducto -> {
                productoActualizado.setId(id);
                productoRepository.save(productoActualizado);
                System.out.println("Producto " + productoActualizado.getNombre() + " actualizado exitosamente");
            },
            () -> {
                throw  new RuntimeException("No se encontro el producto con id " + id);
            }
    );

}
public void actualizarStock(Long id, Integer nuevoStock){
        obtenerPorId(id).ifPresentOrElse(
                objetoEncontrado ->{
                    Integer stockViejo = objetoEncontrado.getStock();
                    objetoEncontrado.setStock(nuevoStock);
                productoRepository.save(objetoEncontrado);
                    System.out.println("Producto " + objetoEncontrado.getNombre() +" con stock Actualizado exitosamente"
                            +"\n"+"Stock anterior: " + stockViejo
                            +"\n"+"Stock actualizado: " + objetoEncontrado.getStock() );
                },
                ()->{
                    throw  new RuntimeException("No se encontro el producto con id " + id);
                }
        );
};

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
