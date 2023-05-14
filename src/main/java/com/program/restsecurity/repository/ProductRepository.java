package com.program.restsecurity.repository;

import com.program.restsecurity.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "products",collectionResourceRel = "products")
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
