package com.sms.project.subcription.controller;

import com.sms.project.subcription.entity.Subscription;
import com.sms.project.subcription.entity.SubscriptionDto;
import com.sms.project.subcription.repository.SubscriptionRepo;
import com.sms.project.subcription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {
    @Autowired
    SubscriptionService subscription;
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @PostMapping("/createSubscription")
    public SubscriptionDto newSubscription(@RequestParam int productId, @RequestParam int planId, @RequestBody SubscriptionDto subscriptionDto) {
        return subscription.createSubscription(productId, planId, subscriptionDto);
    }

    @DeleteMapping("/deleteSubscription/{id}")
    public ResponseEntity<String> removeSubscription(@PathVariable("id") int id) {
        subscription.deleteSubscription(id);
        return new ResponseEntity<>("Subscription removed for this product with your plan", HttpStatus.OK);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public List<Subscription> get() {
        return subscriptionRepo.findAll();
    }

}

