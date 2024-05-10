package com.example.demo.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    
    //atributos
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   @Size(min=2,max=255,message="name must be betwenn 2 and 255")
   private String name;

   @NotBlank
   @Size(min=2,max=255,message="name must be betwenn 2 and 255")
   private String description;

   @NotBlank
   @Min(value = 0, message = "Price must be greater than or equal to 0")
   private double price; 

   @NotBlank
   @Min(value = 0, message = "Stocl must be greater than or equal to 0")
   private int stock;

   @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;


    //acesores
    public Product(){

    }

    public Product(String name,String description,double price,int stock){

        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;

    }

    //getters setters
    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public int geStock(){
        return this.stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    //metodos
    
}
