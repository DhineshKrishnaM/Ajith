package com.sms.project.plandetails.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlanDto {
    private String planName;
    private String planType;
    private int price;
    private int validityDays;
}
