package com.sms.project.payment.service;

import com.sms.project.payment.entity.Payment;
import com.sms.project.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<Payment> getAllPaymentsForAdmin();

    String makePayment(int amount,int planId,int productId,int userid);

    Payment findPaymentByUsingId(int paymentId);

    void deletePaymentById(int paymentId);
    User getLoginUser();
}
