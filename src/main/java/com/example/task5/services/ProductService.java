package com.example.task5.services;

import com.example.task5.models.Product;
import com.example.task5.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getOne(int id) {
        return productRepository.getOne(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
