package com.example.task5.controllers;

import com.example.task5.dao.ProductDAO;
import com.example.task5.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productDAO.selectAll());
        return "products/all";
    }

    @GetMapping("/{id}")
    public String detailProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productDAO.selectById(id));
        return "products/detail";
    }

    @GetMapping("/new")
    public String getNewProductForm(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping
    public String newProduct(@ModelAttribute Product product) {
        productDAO.save(product);
        return "redirect:/products";
    }

    @GetMapping("{id}/edit")
    public String getEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productDAO.selectById(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@ModelAttribute Product product) {
        productDAO.update(product);
        return "redirect:/products/" + product.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productDAO.delete(id);
        return "redirect:/products";
    }
}
