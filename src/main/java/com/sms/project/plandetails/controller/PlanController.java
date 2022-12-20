package com.sms.project.plandetails.controller;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.entity.PlanDto;
import com.sms.project.plandetails.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/getAllPlansByProduct")
    public ResponseEntity<List<PlanDetail>> listOfPlan() {
        List<PlanDetail> list = planService.listOfPlans();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getPlansByType")
    public List<PlanDetail> listOfPlanBasedOnType(@RequestParam String planType) {
        return planService.listOfPlanBasedOnUser(planType);
    }

    @PreAuthorize("hasRole('ROLE_WRITER')")
    @PostMapping("/createNewPlan")
    public PlanDto createNewPlan(@RequestParam int productId, @RequestBody PlanDto newPlan) {
        return planService.postNewPlan(productId, newPlan);
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_ADMIN')")
    @DeleteMapping("/deletePlan")
    public String deletePlan(@RequestParam int planId) {
        planService.deletePlanByWriter(planId);
        return "Plan deleted";
    }

    @PutMapping("/planUpdate/{planId}")
    public PlanDto planUpdate(@PathVariable int planId, @RequestBody PlanDto plan) {
        return planService.updatePlan(planId, plan);
    }
}
