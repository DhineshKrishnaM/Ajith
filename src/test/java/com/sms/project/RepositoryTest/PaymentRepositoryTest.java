package com.sms.project.RepositoryTest;

import com.sms.project.SubscriptionManagementSystemApplication;
import com.sms.project.common.Entity.CommonEntity;
import com.sms.project.payment.entity.Payment;
import com.sms.project.payment.repository.PaymentRepo;
import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.product.entity.Product;
import com.sms.project.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = SubscriptionManagementSystemApplication.class)
public class PaymentRepositoryTest {
    Payment payment = new Payment();
    Payment payment1 = new Payment();
    List allPayments = Arrays.asList(payment, payment1);
    User user = new User();
    Product product = new Product();
    PlanDetail planDetail = new PlanDetail();
    CommonEntity commonEntity = new CommonEntity();
    @Autowired
    private PaymentRepo paymentRepo;

    @BeforeEach
    void setup() {
        payment = Payment.builder()
                .date(LocalDate.now())
                .status("paid")
                .user(user)
                .product(product)
                .planDetail(planDetail)
                .amount(1000)
                .build();
    }
    @DisplayName("Test for make a newPayment")
    @Test
    void savePayment() {
        paymentRepo.save(payment);
        Assertions.assertThat(payment.getDate()).isEqualTo(LocalDate.now());
    }
    @DisplayName("Test for getPayment by using Id")
    @Test
    void getPaymentById() {
        payment = paymentRepo.findById(1).get();
        Assertions.assertThat(payment.getId()).isEqualTo(payment.getId());
    }
    @DisplayName("Test for get all payments")
    @Test
    void getAllPayments() {
        List<Payment> listOfPayments = allPayments;
        Assertions.assertThat(listOfPayments).hasSize(2);
    }
}
