package com.juanfer.market.persistence;

import com.juanfer.market.domain.Product;
import com.juanfer.market.domain.repository.ProductRepository;
import com.juanfer.market.persistence.crud.ProductoCrudRepository;
import com.juanfer.market.persistence.entity.Producto;
import com.juanfer.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNameAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> listaProductos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return  listaProductos.map(productos -> productMapper.toProducts(productos));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(product -> productMapper.toProduct(product));
    }

    @Override
    public Product save(Product product) {
        productoCrudRepository.save(productMapper.toProducto(product));
        return product;
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

    public Optional<List<Producto>> getScarceProductsGreaterThanEqualPrecioVenta(Integer cantidadStock, Boolean estado){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, estado);
    }

}
