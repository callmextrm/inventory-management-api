package com.callmextrm.INVENTORY.controller;


import com.callmextrm.INVENTORY.entity.Product;
import com.callmextrm.INVENTORY.entity.QuantityDTO;
import com.callmextrm.INVENTORY.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService product) {
        this.productService = product;
    }

    //Get all products CONTROLLER
    @GetMapping("/products")
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }

    //Get product By Id CONTROLLER
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //Add product CONTROLLER
    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product product){
        return productService.addProduct(product);
    }

    //Delete product CONTROLLER
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    //Update product CONTROLLER
    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody QuantityDTO quantity){
        return productService.updateProduct(id, quantity.getQuantity());
    }


    //Discontinue products Controller
    @PatchMapping("/products/{id}/discontinue")
    public Product discontinueProduct(@PathVariable Long id){
        return productService.DiscontinueProducts(id);
    }

    }