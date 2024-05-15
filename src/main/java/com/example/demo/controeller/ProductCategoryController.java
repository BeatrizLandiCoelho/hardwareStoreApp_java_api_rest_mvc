package com.example.demo.controeller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProductCategory;
import com.example.demo.repositorys.ProductCategoryRepository;

@RestController
public class ProductCategoryController {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    // Get all ProductCategories
    @GetMapping("/productCategories")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        try {
            List<ProductCategory> productCategories = productCategoryRepository.findAll();
            if (productCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(productCategories, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get ProductCategory by ID
    @GetMapping("/productCategory/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") Long id) {
        try {
            Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
            if (productCategory.isPresent()) {
                return new ResponseEntity<>(productCategory.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new ProductCategory
    @PostMapping("/productCategory")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        try {
            ProductCategory newProductCategory = productCategoryRepository.save(productCategory);
            return new ResponseEntity<>(newProductCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing ProductCategory
    @PutMapping("/productCategory/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") Long id, @RequestBody ProductCategory productCategoryDetails) {
        try {
            Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(id);
            if (optionalProductCategory.isPresent()) {
                ProductCategory productCategory = optionalProductCategory.get();
                // Update fields if needed
                // productCategory.setSomeField(productCategoryDetails.getSomeField());
                ProductCategory updatedProductCategory = productCategoryRepository.save(productCategory);
                return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a ProductCategory
    @DeleteMapping("/productCategory/{id}")
    public ResponseEntity<HttpStatus> deleteProductCategory(@PathVariable("id") Long id) {
        try {
            productCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
