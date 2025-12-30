package com.callmextrm.INVENTORY.controller;


import com.callmextrm.INVENTORY.entity.Product;
import com.callmextrm.INVENTORY.dto.QuantityDTO;
import com.callmextrm.INVENTORY.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService product) {
        this.productService = product;
    }





    @GetMapping("/id_check")
    public String sessionIdCheck(HttpServletRequest http){
        return "The session id is: "+ http.getSession().getId();

    }


    //Get all products CONTROLLER
    @GetMapping("")
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }

    //Get product By Id CONTROLLER
    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //Add product CONTROLLER
    @PostMapping("")
    public Product addProduct(@Valid @RequestBody Product product){
        return productService.addProduct(product);
    }

    //Delete product CONTROLLER
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    //Update product CONTROLLER
    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody QuantityDTO quantity){
        return productService.updateProduct(id, quantity.getQuantity());
    }


    //Discontinue products Controller
    @PatchMapping("{id}/discontinue")
    public Product discontinueProduct(@PathVariable Long id){
        return productService.DiscontinueProducts(id);
    }

    }