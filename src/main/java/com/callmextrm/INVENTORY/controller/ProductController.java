package com.callmextrm.INVENTORY.controller;


import com.callmextrm.INVENTORY.entity.Product;
import com.callmextrm.INVENTORY.dto.ProductDto.QuantityDTO;
import com.callmextrm.INVENTORY.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService product) {
        this.productService = product;
    }



    //Get all products CONTROLLER
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //Get product By Id CONTROLLER
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //Add product CONTROLLER
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        Product saved = productService.addProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    //Delete product CONTROLLER
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    //Update product CONTROLLER
    @PatchMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody QuantityDTO quantity){
        Product product = productService.updateProduct(id, quantity.quantity());
        return ResponseEntity.ok(product);
    }


    //Discontinue products Controller
    @PatchMapping("{id}/discontinue")
    public ResponseEntity<Product> discontinueProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(productService.discontinueProduct(id));
    }
    }