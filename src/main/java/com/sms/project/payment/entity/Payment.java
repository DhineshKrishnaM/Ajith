package com.sms.project.payment.entity;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.product.entity.Product;
import com.sms.project.user.entity.User;
import com.sms.project.common.Entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "payment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends CommonEntity {

    private int amount;

    private LocalDate date;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanDetail planDetail;

}
