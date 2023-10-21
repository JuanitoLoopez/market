package com.juanfer.market.persistence.crud;

import com.juanfer.market.persistence.entity.Compra;
import com.juanfer.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    @Query(value = "SELECT id_compra, id_cliente, fecha, medio_pago, comentario, estado FROM compras;", nativeQuery = true)
    List<Compra> getAll();

    Optional<List<Compra>> findByIdCliente(String idCliente);

}
