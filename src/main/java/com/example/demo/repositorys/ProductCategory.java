package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategory extends JpaRepository<ProductCategory,Long> {
    
}
