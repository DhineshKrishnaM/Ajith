package com.sms.project.RepositoryTest;

import com.sms.project.SubscriptionManagementSystemApplication;
import com.sms.project.subcription.entity.Subscription;
import com.sms.project.subcription.entity.SubscriptionDto;
import com.sms.project.subcription.repository.SubscriptionRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
public class SubscriptionRepoTest {
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Mock
    private Subscription subscription;

    @Mock
    private SubscriptionDto subscriptionDto;
    Subscription subscription1 = new Subscription();
    List<Subscription> list= Arrays.asList(subscription,subscription1);

    @BeforeEach
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
    @DisplayName("Create new Subscription")
    @Test
    void saveSubscription(){
        subscriptionRepo.save(subscription);
        Assertions.assertThat(subscription.getId()).isPositive();
    }
//    @DisplayName("Test case for remove subscription")
//    @Test
//    void deleteSubscription(){
//        List<Subscription> listOfSub=list;
//        subscription=subscriptionRepo.findById(1).get();
//        subscriptionRepo.delete(subscription);
//        Assertions.assertThat(listOfSub.size()).isEqualTo(listOfSub);
//    }
}
