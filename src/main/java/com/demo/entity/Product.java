package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prdId;
    
    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 35, message = "Product name must be between 2 and 35 characters")
    @Column(length = 35, nullable = false)
    private String prodName;
    
    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Product price must be greater than or equal to zero")
    @Column(length = 5, nullable = false)
    private double prdPrice;

    @Size(max = 255, message = "Product description cannot be longer than 255 characters")
    @Column(length = 255)
    private String productDescription;
    
    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, max = 50, message = "Brand name must be between 2 and 50 characters")
    @Column(length = 50, nullable = false)
    private String brand;
    
    @NotNull(message = "Manufacturer cannot be null")
    @Size(min = 2, max = 50, message = "Manufacturer name must be between 2 and 50 characters")
    @Column(length = 50, nullable = false)
    private String manufacturer;

    @Size(max = 255, message = "Product image URL cannot be longer than 255 characters")
    @Column(length = 255)
    private String productImage; // This can be a URL or path to the image file.
}
