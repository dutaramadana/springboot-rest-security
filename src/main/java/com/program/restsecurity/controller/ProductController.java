package com.program.restsecurity.controller;

import com.program.restsecurity.entity.Product;
import com.program.restsecurity.rest.response.Metadata;
import com.program.restsecurity.rest.response.Response;
import com.program.restsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    private Response response = new Response();
    private Metadata metadata = new Metadata();


    @GetMapping("/products")
    public ResponseEntity<Response> findAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") int size){

        List<Product> productList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAll(pageable);

        productList = productPage.getContent();

        metadata(pageable, productPage);

        response("Get All Products", productList, HttpStatus.OK, HttpStatus.OK.value(), metadata);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Response> findProductsById(@PathVariable int id){

        Product product = productService.findById(id);

        response("Get product by ID", product, HttpStatus.OK, HttpStatus.OK.value(), metadata);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @PostMapping("/products")
    public ResponseEntity<Response> createProduct(@RequestBody Product product){

        Product createdProduct = productService.save(product);

        response("Create new product", createdProduct, HttpStatus.CREATED, HttpStatus.CREATED.value());

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable int id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable int id,
                                                  @RequestBody Product product){

        Product productById = productService.findById(id);

        productById.setName(product.getName());
        productById.setDescription(product.getDescription());
        productById.setPrice(product.getPrice());

        Product updatedProduct = productService.save(productById);

        response("Product with ID: " + id + " updated successfully", updatedProduct, HttpStatus.OK, HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private void metadata(Pageable pageable, Page<Product> productPage) {
        metadata.setPageable(pageable);
        metadata.setTotalPages(productPage.getTotalPages());
        metadata.setTotalElements(productPage.getTotalElements());
        metadata.setLast(productPage.isLast());
        metadata.setSize(productPage.getSize());
        metadata.setNumber(productPage.getNumber());
        metadata.setNumberOfElements(productPage.getNumberOfElements());
        metadata.setFirst(productPage.isFirst());
        metadata.setEmpty(productPage.isEmpty());
    }

    private void response(String message, Object payload, HttpStatus httpStatus, int statusCode){
        response.setMessage(message);
        response.setPayload(payload);
        response.setHttpStatus(httpStatus);
        response.setStatusCode(statusCode);
    }

    private void response(String message, Object payload, HttpStatus httpStatus, int statusCode, Metadata metadata){
        response.setMessage(message);
        response.setPayload(payload);
        response.setHttpStatus(httpStatus);
        response.setStatusCode(statusCode);
        response.setMetadata(metadata);
    }


}
