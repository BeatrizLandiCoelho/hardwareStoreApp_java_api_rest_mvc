package com.example.demo.controeller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Category;
import com.example.demo.repositorys.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CategoryController {

    // autowired
    @Autowired
    CategoryRepository categoryRepository;

    // metodos

    // Get_________________________________________________________________________________________

    @GetMapping("/categorys")
    public ResponseEntity<List<Category>> getAllCategorys() {

        try {

            List<Category> categoryList = categoryRepository.findAll();

            if (categoryList.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {

                return new ResponseEntity<>(categoryList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get by id
    // _________________________________________________________________________________________

    @GetMapping("category{id}")
    public ResponseEntity<Category> getCategoryById(@RequestParam Long id) {

        try {

            Optional<Category> category = categoryRepository.findById(id);

            if (category.isPresent()) {
                return new ResponseEntity<>(category.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Post
    // _________________________________________________________________________________________
    @PostMapping("/category")
    public ResponseEntity<Category> postCategory(@RequestBody Category category) {

        try {

            Category newCategory = categoryRepository.save(category);

            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Updtae
    // _________________________________________________________________________________________
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateUser(@PathVariable("id") Long id, @RequestBody Category categoryData) {

        try {
            Category existingCategory = categoryRepository.findById(id).orElse(null);

            if (existingCategory != null) {
                existingCategory.setName(categoryData.getName());

                Category updatedCategory = categoryRepository.save(existingCategory);
                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Delete
    // _________________________________________________________________________________________
    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id) {

        try {

            Category existingCategory = categoryRepository.findById(id).orElse(null);

            if (existingCategory != null) {
                categoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

}
