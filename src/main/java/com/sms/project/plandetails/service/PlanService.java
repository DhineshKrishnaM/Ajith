package com.sms.project.plandetails.service;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.entity.PlanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanService {
    List<PlanDetail> listOfPlans();

    List<PlanDetail> listOfPlanBasedOnUser(String planType);

    PlanDto postNewPlan(int productId, PlanDto newPlan);

    String deletePlanByWriter(int planId);

    PlanDto updatePlan(int planId,PlanDto plan);
}
