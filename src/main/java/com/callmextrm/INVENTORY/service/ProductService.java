package com.callmextrm.INVENTORY.service;

import com.callmextrm.INVENTORY.entity.Product;
import com.callmextrm.INVENTORY.entity.Status;
import com.callmextrm.INVENTORY.exception.Exceptions.Discontinued;
import com.callmextrm.INVENTORY.exception.Exceptions.Quantity;
import com.callmextrm.INVENTORY.exception.Exceptions.ResourceNotFound;
import com.callmextrm.INVENTORY.repository.Productrepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Productrepo productDao;

    public ProductService(Productrepo productDao) {
        this.productDao = productDao;
    }
    //Add product SERVICE
    public Product addProduct(Product product){
        if (product.getQuantity()>=0){
        product.setStatus(Status.Active);}
        else throw new Quantity("Quantity can't be negative");

        return productDao.save(product);
    }

    //Get product by id SERVICE
    public Product getProductById(Long id){
        return productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found: " + id));
    }

    //Get all products SERVICE
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    //Update product SERVICE
    public Product updateProduct(Long id,Integer quantity ) {
        Product exisitingProduct = productDao.findById(id).orElseThrow(() -> new ResourceNotFound("Product not found"));
        if (exisitingProduct.getStatus()==Status.Discontinued){
        throw new Discontinued("Can't update discontinued products");
        }
        if (quantity==0){
            exisitingProduct.setQuantity(quantity);
            exisitingProduct.setStatus(Status.OUT_OF_STOCK);
        } else if (quantity>0) {
            exisitingProduct.setQuantity(quantity);
            exisitingProduct.setStatus(Status.Active);
        } else throw new Quantity("Quantity can't be negative");
        return productDao.save(exisitingProduct);

    }

    //Delete product SERVICE
    public void deleteProduct(Long id) {
        if (!productDao.existsById(id)) {
            throw new ResourceNotFound("Product not found: " + id);
        }
        productDao.deleteById(id);
    }

    //Discontinue products SERVICE
    public Product discontinueProduct(Long id){
        Product product = productDao.findById(id).orElseThrow(()-> new ResourceNotFound("Product not found"));
        if(product.getStatus()==Status.Discontinued){
            throw new Discontinued("The product is already Discontinued");
        } else
            product.setStatus(Status.Discontinued);
        return productDao.save(product);


    }
}
