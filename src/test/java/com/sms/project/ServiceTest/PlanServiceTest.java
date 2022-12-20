package com.sms.project.ServiceTest;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.plandetails.entity.PlanDto;
import com.sms.project.plandetails.repo.PlanRepository;
import com.sms.project.plandetails.service.PlanServiceImpl;
import com.sms.project.product.dto.ProductDto;
import com.sms.project.product.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {
    @Mock
    ModelMapper modelMapper;

    PlanDetail plan = new PlanDetail();
    PlanDto planDto = new PlanDto();
    Optional<PlanDetail> plan1 = Optional.of(new PlanDetail());
    List<PlanDetail> planList = new ArrayList<>();
    @Mock
    PlanRepository planRepository;
    @InjectMocks
    PlanServiceImpl planService;
    @BeforeEach
    void setup(){
        plan=PlanDetail.builder()
                .planName("5GB/month")
                .planType("B2C")
                .validityDays(30)
                .price(1500)
                .build();
        planDto=PlanDto.builder()
                .planName("5GB/month")
                .planType("B2C")
                .validityDays(30)
                .price(1500)
                .build();
    }
//    @Test
//    void saveNewPlan(){
//        Mockito.when(modelMapper.map(planDto,PlanDetail.class)).thenReturn(plan);
//        Mockito.when(planRepository.save(plan)).thenReturn(plan);
//        Mockito.when(modelMapper.map(plan,PlanDto.class)).thenReturn(planDto);
//        Assertions.assertThat(planService.postNewPlan(1,planDto)).isEqualTo(planDto);
//    }
}
