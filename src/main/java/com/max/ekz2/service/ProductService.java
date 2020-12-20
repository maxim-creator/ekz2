package com.max.ekz2.service;

import com.max.ekz2.models.Product;
import com.max.ekz2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    public void edit(Product product){
        deleteById(product.getId());
        productRepository.save(product);
    }
}
