package com.example.task5.dao;

import com.example.task5.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private List<Product> products = new ArrayList<>();

    {
        products.add(new Product(1, "table IKEA", 167, 10));
        products.add(new Product(2, "pen", 2, 9889));
        products.add(new Product(3, "notebook", 167, 98));
    }

    public List<Product> selectAll() {
        return products;
    }

    public Product selectById(int id) {
        for (Product p: products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public Product update(Product product) {
        for (Product p: products) {
            if (p.getId() == product.getId()) {
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                return p;
            }
        }
        return null;
    }

    public void delete(int id) {
        for (Product p: products) {
            if (p.getId() == id) {
                products.remove(p);
                return;
            }
        }
    }
}
