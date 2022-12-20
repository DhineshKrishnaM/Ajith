package com.sms.project.product.service;

import com.sms.project.product.dto.ProductDto;
import com.sms.project.product.entity.Product;
import com.sms.project.product.repo.ProductRepo;
import com.sms.project.utility.errorcode.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> getAllProducts() {
        log.info("List of products are getting....");
        List<Product> prodList = productRepo.findAll();
        return prodList.stream().filter(product -> product.getDeleted_at() == null).collect(Collectors.toList());
    }

    @Override
    public String createNewProduct(ProductDto product1dto) {
        log.info("Product created");
        Product prod = modelMapper.map(product1dto, Product.class);
        productRepo.save(prod);
        return "New product created";
    }

    @Override
    public String deleteProductById(int id) {
        log.info("Product have been deleting..");
        Product prod = productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND));
        prod.setDeleted_at(LocalDate.now());
        productRepo.saveAndFlush(prod);
        return "Product Deleted";
    }

    @Override
    public Optional<Product> getById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public String updateNewProduct(ProductDto product) {
        Product prod = modelMapper.map(product, Product.class);
        productRepo.save(prod);
        log.info("Product has been updated..");
        return "Values are updated";
    }
}
