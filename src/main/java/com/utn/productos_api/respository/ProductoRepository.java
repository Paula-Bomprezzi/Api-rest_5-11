package com.utn.productos_api.respository;

import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Métodos genéricos de un repo, los trae por implementar la interfaz JpaRepository

    //Método personalizado, Spring crea automáticamente la query
    public List<Producto> findByCategoria(Categoria categoria);
}
