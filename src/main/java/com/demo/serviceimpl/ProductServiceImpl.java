package com.demo.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.entity.Product;
import com.demo.repository.ProductRespository;
import com.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository prepo;

    @Override
    public void addProduct(Product product) {
        // Validate product name
        if (product.getProdName() == null || product.getProdName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        // Validate product price
        if (product.getPrdPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }

        // Validate product description (optional, based on the field constraints)
        if (product.getProductDescription() != null && product.getProductDescription().length() > 255) {
            throw new IllegalArgumentException("Product description cannot be longer than 255 characters");
        }

        // Validate brand
        if (product.getBrand() == null || product.getBrand().length() < 2 || product.getBrand().length() > 50) {
            throw new IllegalArgumentException("Brand name must be between 2 and 50 characters");
        }

        // Validate manufacturer
        if (product.getManufacturer() == null || product.getManufacturer().length() < 2 || product.getManufacturer().length() > 50) {
            throw new IllegalArgumentException("Manufacturer name must be between 2 and 50 characters");
        }

        // Validate product image URL (optional, based on your requirements)
        if (product.getProductImage() != null && product.getProductImage().length() > 255) {
            throw new IllegalArgumentException("Product image URL cannot be longer than 255 characters");
        }

        // Save the product if all validations pass
        prepo.save(product);
    }

    @Override
    public List<Product> allProducts() {
        return prepo.findAll();
    }
}
