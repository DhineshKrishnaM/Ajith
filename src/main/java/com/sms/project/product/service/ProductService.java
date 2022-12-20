package com.sms.project.product.service;

import com.sms.project.product.dto.ProductDto;
import com.sms.project.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    String createNewProduct(ProductDto productdto);

    String deleteProductById(int id);

    Optional<Product> getById(int id);

    String updateNewProduct(ProductDto product);
}
