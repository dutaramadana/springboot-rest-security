package com.program.restsecurity.service;

import com.program.restsecurity.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findById(int id);

    Product save(Product product);

    void deleteById(int id);


}
