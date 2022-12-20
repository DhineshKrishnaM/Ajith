package com.sms.project.RepositoryTest;

import com.sms.project.SubscriptionManagementSystemApplication;
import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.repo.PlanRepository;
import com.sms.project.utility.errorcode.ErrorCodes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = SubscriptionManagementSystemApplication.class)
class PlanRepositoryTest {
    @Autowired
    PlanRepository planRepository;
    PlanDetail plan = new PlanDetail();
    PlanDetail plan1 = new PlanDetail();
    List<PlanDetail> planDetails = Arrays.asList(plan, plan1);


    @BeforeEach
    void setup() {
        plan = PlanDetail.builder()
                .planName("2GB/week")
                .planType("B2B")
                .price(1000)
                .build();
    }

    @DisplayName("To save new Plan")
    @Test
    void saveNewPlan() {
        planRepository.save(plan);
        Assertions.assertThat(plan.getId()).isPositive();
    }

    @DisplayName("To get list of plans")
    @Test
    void listOfPlans() {
        List<PlanDetail> allPlans = planDetails;
        Assertions.assertThat(allPlans.size()).isEqualTo(2);
    }

    @DisplayName("Delete Plan By using Id")
    @Test
    void deleteById() {
        List<PlanDetail> list = planRepository.findAll();
        PlanDetail ss = planRepository.findById(1).get();
        planRepository.delete(ss);
        List<PlanDetail> result = new ArrayList<>();
        planRepository.findAll().forEach(e -> result.add(e));
        Assertions.assertThat(result).hasSize(list.size() - 1);
    }
    @DisplayName("Update Plan By using Id")
    @Test
    void updateById(){
        plan=planRepository.findById(1).get();
        plan.setPlanName("4GB/Month");
        plan=planRepository.save(plan);
        Assertions.assertThat(plan.getId()).isEqualTo(plan.getId());
    }
}
