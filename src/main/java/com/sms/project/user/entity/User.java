package com.sms.project.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sms.project.payment.entity.Payment;
import com.sms.project.subcription.entity.Subscription;
import com.sms.project.utility.errorcode.ErrorCodes;
import com.sms.project.common.Entity.CommonEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class User extends CommonEntity implements Serializable {

    @NotNull(message = ErrorCodes.USER_NAME_NOTNULL)
    private String username;

    @Column(unique = true)
    @NotNull(message = ErrorCodes.EMAIL_NOT_NULL)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;

    public User(int id, LocalDate deleted_at, String username, String email, String password, String role) {
        super(id, deleted_at);
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

//    @JsonIgnoreProperties("user")
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private List<Payment> payments;

    @JsonIgnoreProperties("user")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<Subscription> subscriptions;
}
