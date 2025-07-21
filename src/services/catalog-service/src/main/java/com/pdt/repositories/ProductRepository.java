package com.pdt.repositories;

import com.pdt.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Product.ProductId> {
    Iterable<Product> findByCategory(String category);
}
