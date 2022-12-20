package com.sms.project.RepositoryTest;

import com.sms.project.SubscriptionManagementSystemApplication;
import com.sms.project.product.entity.Product;
import com.sms.project.product.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = SubscriptionManagementSystemApplication.class)
class ProductRepositoryTest {
    @Autowired
    private ProductRepo productRepo;

    @DisplayName("Save new product")
    @Test
    void saveProduct() {
        Product product = Product.builder()
                .productName("Basic Python")
                .description("From begining to end")
                .build();
        productRepo.save(product);
        Assertions.assertThat(product.getId()).isPositive();
    }

    @DisplayName("To get list of products")
    @Test
    void testListProduct() {
        List<Product> products = productRepo.findAll();
        Assertions.assertThat(products).isNotEmpty();
    }

    @DisplayName("Get Product By using Id")
    @Test
    void getById() {
        Optional<Product> productData = productRepo.findById(16);
        Assertions.assertThat(productData.get().getId()).isEqualTo(16);
    }

    @DisplayName("Update Product By using Id")
    @Test
    void updateProduct() {
        Product product = productRepo.findById(16).get();
        product.setProductName("JavaScript");
        Product product1 = productRepo.save(product);
        Assertions.assertThat(product.getProductName()).isEqualTo(product1.getProductName());
    }

    @DisplayName("Delete Product By using Id")
    @Test
    void deleteById() {
        List<Product> list = productRepo.findAll();
        Product ss = productRepo.findById(16).get();
        productRepo.delete(ss);
        List<Product> result = new ArrayList<>();
        productRepo.findAll().forEach(e -> result.add(e));
        Assertions.assertThat(result).hasSize(list.size() - 1);
    }

}
