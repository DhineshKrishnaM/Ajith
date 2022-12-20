package com.sms.project.subcription.entity;

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
@Table(name = "subscription")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends CommonEntity {

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")        //correct
    private PlanDetail planDetail;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
