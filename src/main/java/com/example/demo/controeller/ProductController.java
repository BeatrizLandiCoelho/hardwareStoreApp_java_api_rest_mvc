package com.example.demo.controeller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Product;
import com.example.demo.repositorys.ProductRepository;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    // metodos

    // Get
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

    // GET by id
    @GetMapping("/products/{id}") // Use path variable for ID
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    
        try{

            Optional<Product> product = productRepository.findById(id);
    
            if (product.isPresent()) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

         }catch(Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post
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

    // Update
    

}
