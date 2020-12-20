package com.max.ekz2.repositories;

import com.max.ekz2.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
}
