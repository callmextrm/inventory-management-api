package com.callmextrm.INVENTORY.repository;

import com.callmextrm.INVENTORY.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepo extends JpaRepository<Product,Long> {
}
