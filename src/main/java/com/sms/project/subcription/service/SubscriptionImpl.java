package com.sms.project.subcription.service;

import com.sms.project.payment.service.PaymentServiceImpl;
import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.repo.PlanRepository;
import com.sms.project.product.entity.Product;
import com.sms.project.product.repo.ProductRepo;
import com.sms.project.subcription.entity.Subscription;
import com.sms.project.subcription.entity.SubscriptionDto;
import com.sms.project.subcription.repository.SubscriptionRepo;
import com.sms.project.user.entity.User;
import com.sms.project.utility.errorcode.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class SubscriptionImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public SubscriptionDto createSubscription(int productId, int planId, SubscriptionDto subscriptionDto) {
        log.info("Subscription process is going on now...");
        User userInfo = paymentService.getLoginUser();
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PRODUCT_NOT_FOUND));
        PlanDetail plan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PLAN_NOT_FOUND));
        Subscription subscription = modelMapper.map(subscriptionDto, Subscription.class);
        subscription.setUser(userInfo);
        subscription.setProduct(product);
        subscription.setPlanDetail(plan);
        subscription.setStatus("Active");
        subscription.setStartDate(LocalDate.now());
        LocalDate today = LocalDate.now();
        subscription.setEndDate(today.plusDays(plan.getValidityDays()));
        subscriptionRepo.save(subscription);
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

    @Override
    public String deleteSubscription(int id) {
        log.info("To remove subscription");
        Subscription subDetails = subscriptionRepo.findById(id).orElseThrow(null);
        subscriptionRepo.delete(subDetails);
        return "Subscription removed";
    }
}
