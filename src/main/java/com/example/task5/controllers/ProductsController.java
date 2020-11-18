package com.example.task5.controllers;

import com.example.task5.entities.Product;
import com.example.task5.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/all";
    }

    @GetMapping("/{id}")
    public String detailProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getOne(id));
        return "products/detail";
    }

    @GetMapping("/new")
    public String getNewProductForm(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping
    public String newProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("{id}/edit")
    public String getEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getOne(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("VALIDATION ERROR: " + bindingResult.getFieldErrors().toString());
            return "products/edit";
        }
        productService.save(product);
        return String.format("redirect:/products/%s",product.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
