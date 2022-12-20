package com.sms.project.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.project.payment.entity.Payment;
import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.subcription.entity.Subscription;
import com.sms.project.common.Entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends CommonEntity {
    private String productName;
    private String description;

    @JsonIgnoreProperties("product")
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PlanDetail.class, mappedBy = "product")
    private List<PlanDetail> planDetailList;

    @JsonIgnoreProperties("product")
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Subscription.class, mappedBy = "product")
    private List<Subscription> subscription;

    @JsonIgnoreProperties("product")
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Payment.class, mappedBy = "product")
    private List<Payment> payments;
}
