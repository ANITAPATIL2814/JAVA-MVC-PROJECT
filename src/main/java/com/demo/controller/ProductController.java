package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.demo.entity.Product;
import com.demo.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService prdService;
	
	@GetMapping("/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@PostMapping("/add")
	public String addProduct(@ModelAttribute Product product) {
		prdService.addProduct(product);
		return "redirect:/products/view";
	}
	
	@GetMapping("/view")
	public String viewProducts(Model model) {
	    List<Product> products = prdService.allProducts();
	    
	    // Log the products list to see if data is being fetched correctly
	    System.out.println(products);
	    
	    model.addAttribute("products", products);
	    return "viewProducts"; // Ensure this matches the name of your Thymeleaf template
	}


	
}
