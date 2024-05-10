package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
