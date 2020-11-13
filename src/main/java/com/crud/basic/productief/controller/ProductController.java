package com.crud.basic.productief.controller;

import com.crud.basic.productief.model.ProductModel;
import com.crud.basic.productief.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    private ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductModel productModel) {
        return new ResponseEntity<ProductModel>(productRepository.save(productModel), HttpStatus.CREATED);
    }

    @GetMapping("/product")
    private ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> produtos = productRepository.findAll();
        if (produtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ProductModel>>(produtos, HttpStatus.OK);
        }
    }

    @GetMapping("/product/{id}")
    private ResponseEntity<ProductModel> getProductById(@PathVariable(value = "id") long id) {
        Optional<ProductModel> productResponse = productRepository.findById(id);
        if (productResponse.isPresent()) {
            return new ResponseEntity<ProductModel>(productResponse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/product/{id}")
    private ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") long id, @RequestBody @Valid ProductModel productModel) {
        Optional<ProductModel> productResponse = productRepository.findById(id);
        if (productResponse.isPresent()) {
            productModel.setId(productResponse.get().getId());
            return new ResponseEntity<ProductModel>(productRepository.save(productModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{id}")
    private ResponseEntity<ProductModel> deleteProduct(@PathVariable(value = "id") long id) {
        Optional<ProductModel> productResponse = productRepository.findById(id);
        if (productResponse.isPresent()) {
            productRepository.delete(productResponse.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
