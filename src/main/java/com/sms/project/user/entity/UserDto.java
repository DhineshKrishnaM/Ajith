package com.sms.project.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.project.common.Entity.CommonEntity;
import com.sms.project.payment.entity.Payment;
import com.sms.project.subcription.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends CommonEntity {
    private String username;
    private String email;
    private String password;
    private String role;
//    @JsonIgnoreProperties("user")
//    private List<Payment> payments;
//    private List<Subscription> subscriptions;
}
