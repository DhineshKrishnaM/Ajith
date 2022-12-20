package com.sms.project.ControllerTest;

import com.sms.project.subcription.controller.SubscriptionController;
import com.sms.project.subcription.entity.Subscription;
import com.sms.project.subcription.entity.SubscriptionDto;
import com.sms.project.subcription.service.SubscriptionImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SubscriptionControllerTest {
    Subscription subscription=new Subscription();
    SubscriptionDto subscriptionDto=new SubscriptionDto();
    List<Subscription> list=new ArrayList<>();

    @Mock
    SubscriptionImpl subscriptionImpl;
    @InjectMocks
    SubscriptionController subscriptionController;
    void setup(){
        subscription=Subscription.builder()
                .status("active")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(7))
                .build();
        subscriptionDto=SubscriptionDto.builder()
                .status("active")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(7))
                .build();
    }
    @Test
    void saveSubscription(){
        Mockito.when(subscriptionImpl.createSubscription(1,1,subscriptionDto)).thenReturn(subscriptionDto);
        Assertions.assertThat(subscriptionController.newSubscription(1,1,subscriptionDto)).isEqualTo(subscriptionDto);
    }
    @Test
    void deleteSubscription(){
        Mockito.when(subscriptionImpl.deleteSubscription(1)).thenReturn("Subscription removed");
        Assertions.assertThat(subscriptionController.removeSubscription(1).getStatusCodeValue()).isEqualTo(200);
    }
}
