package com.sms.project.payment.service;

import com.sms.project.payment.entity.Payment;
import com.sms.project.payment.repository.PaymentRepo;
import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.repo.PlanRepository;
import com.sms.project.product.entity.Product;
import com.sms.project.product.repo.ProductRepo;
import com.sms.project.user.entity.User;
import com.sms.project.user.repo.UserRepo;
import com.sms.project.utility.EmailService;
import com.sms.project.utility.errorcode.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PlanRepository planRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmailService emailService;

    @Override
    public List<Payment> getAllPaymentsForAdmin() {
        log.info("List of payment is fetching....");
        List<Payment> list = paymentRepo.findAll();
        return list.stream().filter(payment -> payment.getDeleted_at() == null).collect(Collectors.toList());
    }

    @Override
    public String makePayment(int amount, int planId, int productId, int userid) {
        log.info("Payment is processing....");
        User user = userRepo.findById(userid).get();
        Product product = productRepo.findById(productId).get();
        PlanDetail plan = planRepository.findById(planId).get();
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setProduct(product);
        payment.setPlanDetail(plan);
        if (amount == plan.getPrice()) {
            payment.setAmount(plan.getPrice());
        }
        payment.setDate(LocalDate.now());
        paymentRepo.save(payment);
        String userName=user.getUsername();
        emailService.mailForMessage(user.getEmail(),"<h1>Payment has been completed</h1>"
                ,"Payment Successful");
        return "Payment completed";
    }

    @Override
    public Payment findPaymentByUsingId(int paymentId) {
        log.info(paymentId + " Id is taken value and fetching....");
        return paymentRepo.findById(paymentId).get();

    }

    @Override
    public void deletePaymentById(int paymentId) {
        log.info("payment deletion is processing");
        Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PAYMENT_NOT_FOUND));
        payment.setDeleted_at(LocalDate.now());
        paymentRepo.saveAndFlush(payment);
    }

    /**
     * To get current user details
     * @return UserEntity
     */
    public User getLoginUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        User loginUser=userRepo.findByEmail(email).get();
        return loginUser;
    }

}
