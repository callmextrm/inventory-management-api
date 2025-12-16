package com.callmextrm.INVENTORY.service;

import com.callmextrm.INVENTORY.entity.Product;
import com.callmextrm.INVENTORY.entity.Status;
import com.callmextrm.INVENTORY.exception.Discontinued;
import com.callmextrm.INVENTORY.exception.ProductNotFound;
import com.callmextrm.INVENTORY.exception.Quantity;
import com.callmextrm.INVENTORY.repository.Productrepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final Productrepo productDao;

    public ProductService(Productrepo productDao) {
        this.productDao = productDao;
    }
    //Add product
    public Product addProduct(Product product){
        if (product.getQuantity()>=0){
        product.setStatus(Status.Active);}
        else throw new Quantity("Quantity is negative");
        return productDao.save(product);
    }

    //Get product by id
    public Optional<Product>getProductById(Long id){
        return productDao.findById(id);
    }

    //Get all products
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    //Update product
    public Product updateProduct(Long id,Integer quantity ) {
        Product exisitingProduct = productDao.findById(id).orElseThrow(() -> new ProductNotFound("Product not found"));
        if (exisitingProduct.getStatus()==Status.Discontinued){
        throw new Discontinued("Can't update discontinued products");
        }
        if (quantity==0){
            exisitingProduct.setQuantity(quantity);
            exisitingProduct.setStatus(Status.OUT_OF_STOCK);
        } else if (quantity>0) {
            exisitingProduct.setQuantity(quantity);
            exisitingProduct.setStatus(Status.Active);
        } else throw new RuntimeException("Quantity can't be negative");
        return productDao.save(exisitingProduct);

    }

    //Delete product
    public void deleteProduct(Long id){
        productDao.deleteById(id);
    }

    //Discontinue products
    public Product DiscontinueProducts(Long id){
        Product product = productDao.findById(id).orElseThrow(()-> new ProductNotFound("Product not found"));
        if(product.getStatus()==Status.Discontinued){
            throw new Discontinued("The product is already Discontinued");
        } else
            product.setStatus(Status.Discontinued);
        return productDao.save(product);


    }
}
