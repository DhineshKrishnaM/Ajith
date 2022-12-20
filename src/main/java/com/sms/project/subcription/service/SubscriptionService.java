package com.sms.project.subcription.service;

import com.sms.project.subcription.entity.SubscriptionDto;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {
    SubscriptionDto createSubscription(int productId, int planId, SubscriptionDto subscriptionDto);

    String deleteSubscription(int id);
}
