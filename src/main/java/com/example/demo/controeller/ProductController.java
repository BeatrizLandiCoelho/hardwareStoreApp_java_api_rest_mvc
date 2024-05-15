package com.example.demo.controeller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositorys.ProductRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    // metodos

    // Get
    // _________________________________________________________________________________________

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        try {

            List<Product> productList = productRepository.findAll();

            if (productList.isEmpty()) {

                return new ResponseEntity<>(productList, HttpStatus.NOT_FOUND);

            } else {

                return new ResponseEntity<>(productList, HttpStatus.OK);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // GET by_________________________________________________________________________________________

    @GetMapping("/product/{id}") 
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

        try {

            Optional<Product> product = productRepository.findById(id);

            if (product.isPresent()) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post_________________________________________________________________________________________

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Product savedProduct;
        try {
            savedProduct = productRepository.save(product);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Update_________________________________________________________________________________________
        @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails) {

        try {

            Optional<Product> optionalProduct = productRepository.findById(id);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setName(productDetails.getName()); 

                Product updatedProduct = productRepository.save(product);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Delete__________________________________________________________________________________________________
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id) {

        try {

            Product existingProduct = productRepository.findById(id).orElse(null);

            if (existingProduct != null) {
                productRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
