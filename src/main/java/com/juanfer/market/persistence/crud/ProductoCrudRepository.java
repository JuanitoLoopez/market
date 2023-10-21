package com.juanfer.market.persistence.crud;

import com.juanfer.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNameAsc(Integer idCategoria);
    @Query(value = "SELECT * FROM productos WHERE cantidad_stock > ? and estado = ?", nativeQuery = true)
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);
    Optional<List<Producto>> findByPrecioVentaGreaterThanEqualAndCantidadStockLessThanOrderByNombre(Double precioVenta, Integer cantidadStock);
}
