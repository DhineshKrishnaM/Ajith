package com.sms.project.subcription.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    private String status;

    private LocalDate startDate;

    private LocalDate endDate;
}
