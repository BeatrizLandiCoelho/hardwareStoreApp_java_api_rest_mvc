package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{
    
}
