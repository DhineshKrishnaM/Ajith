package com.sms.project.plandetails.service;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.entity.PlanDto;
import com.sms.project.plandetails.repo.PlanRepository;
import com.sms.project.product.entity.Product;
import com.sms.project.product.repo.ProductRepo;
import com.sms.project.utility.errorcode.ErrorCodes;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PlanServiceImpl implements PlanService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<PlanDetail> listOfPlans() {
        List<PlanDetail> planList = planRepository.findAll();
        log.info("List of plan executed Successfully");
        List<PlanDetail> sortList = planList.stream().filter(planDetail -> planDetail.getDeleted_at() == null).collect(Collectors.toList());
        return sortList;
    }

    @Override
    public List<PlanDetail> listOfPlanBasedOnUser(String planType) {
        log.info("List of plan based on PlanType executed");
        return planRepository.findAllByPlanType(planType);
    }

    @Override
    public PlanDto postNewPlan(int productId, PlanDto newPlan) {
        log.info("New plan added");
        Optional<Product> productGet = productRepo.findById(productId);
        PlanDetail planDetail = modelMapper.map(newPlan, PlanDetail.class);
        if (productGet.isPresent()) {
            planDetail.setProduct(productGet.get());
        }
        planRepository.save(planDetail);
        PlanDto planDto = modelMapper.map(planDetail, PlanDto.class);
        return planDto;
    }

    @Override
    public String deletePlanByWriter(int planId) {
        log.info(planId + " Plan has been deleted");
        PlanDetail planDetail = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PLAN_NOT_FOUND));
        planDetail.setDeleted_at(LocalDate.now());
        planRepository.saveAndFlush(planDetail);
        return "Plan Deleted";
    }

    @Override
    public PlanDto updatePlan(int planId, PlanDto plan) {
        PlanDetail planDet = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.PLAN_NOT_FOUND));
        planDet.setPlanName(plan.getPlanName());
        planDet.setPlanType(plan.getPlanType());
        planRepository.save(planDet);
        log.info("The Plan Details updated for id: " + planId);
        return modelMapper.map(planDet, PlanDto.class);
    }
}
