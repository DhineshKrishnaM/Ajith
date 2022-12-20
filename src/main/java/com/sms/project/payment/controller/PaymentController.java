package com.sms.project.payment.controller;

import com.sms.project.payment.entity.Payment;
import com.sms.project.payment.service.PaymentService;
import com.sms.project.payment.service.PaymentServiceImpl;
import com.sms.project.user.entity.User;
import com.sms.project.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PaymentService paymentService;


    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPaymentsForAdmin();
    }

    @PostMapping("/createPayment")
    public ResponseEntity<String> doPayment(@RequestParam int amount,@RequestParam int planId,
                                            @RequestParam int productId){
        User userData=paymentService.getLoginUser();
        paymentService.makePayment(amount,planId,productId,userData.getId());
        return new ResponseEntity<>("Successfully paid", HttpStatus.ACCEPTED);
    }
    @GetMapping("/paymentById")
    public Payment paymentById(@RequestParam int paymentId){
        return paymentService.findPaymentByUsingId(paymentId);
    }
    @DeleteMapping("/deletePaymentByUserId")
    public String deleteId(@RequestParam int paymentId){
        paymentService.deletePaymentById(paymentId);
        return "Payment deleted";
    }

}
