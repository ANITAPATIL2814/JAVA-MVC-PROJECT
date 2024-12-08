package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.demo.entity.Customer;
import com.demo.serviceimpl.CustomerServiceImpl;

import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    // Display the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";  
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer, Model model) {
        // Check if email already exists
        if (customerService.findByEmail(customer.getEmail()) != null) {
            model.addAttribute("emailError", "Email is already in use!");
            return "register";  // Show registration form again with error message
        }

        customerService.registerCustomer(customer);
        return "redirect:/customer/login";  // Redirect to login after successful registration
    }

    // Display the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  
    }

    // This method is not needed as Spring Security will handle login automatically
    /*@PostMapping("/login")
    public String loginCustomer(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Spring Security will handle authentication, just return if successful
        return "redirect:/customer/dashboard";
    }*/

    // Display user dashboard after successful login
    @GetMapping("/dashboard")
    public String showDashboard() {
        // Get the logged-in user's details (via Spring Security)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "addProduct";  // dashboard.html (User dashboard)
    }
}
