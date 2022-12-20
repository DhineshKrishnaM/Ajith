package com.sms.project.plandetails.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.project.payment.entity.Payment;
import com.sms.project.product.entity.Product;
import com.sms.project.common.Entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "plan")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDetail extends CommonEntity {
    private String planName;
    private String planType;
    private int price;
    private int validityDays;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @JsonIgnoreProperties("plan")
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Payment.class,mappedBy = "planDetail")
    private List<Payment> payment;

    public PlanDetail(int id, LocalDate date, String planName, String planType, int price) {
        super.setId(id);
        super.setDeleted_at(date);
        this.planName = planName;
        this.planType = planType;
        this.price = price;
    }
}
