package com.sms.project.product.controller;


import com.sms.project.product.dto.ProductDto;
import com.sms.project.product.entity.Product;
import com.sms.project.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_WRITER')")
    @PostMapping("/createProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody ProductDto productdto) {
        productService.createNewProduct(productdto);
        return new ResponseEntity<>("Product created", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProductById")
    public ResponseEntity<String> deleteExistProduct(@RequestParam int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product has been deleted", HttpStatus.OK);
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<String> updateExistValue(@PathVariable("id") int id, @RequestBody ProductDto productdto) {
        return productService.getById(id).map(product1 -> {
            product1.setProductName(productdto.getProductName());
            product1.setDescription(productdto.getDescription());
            ProductDto product2 = modelMapper.map(product1, ProductDto.class);
            productService.updateNewProduct(product2);
            return new ResponseEntity<>("Product details updated Successfully", HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
        Product product = productService.getById(productId).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
