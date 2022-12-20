package com.sms.project.subcription.repository;

import com.sms.project.subcription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription,Integer> {
//    @Query(value = "select New com.sms.project.subcription.entity.Subscription(u.id,u.deleted_at,u.endDate,u.startDate,u.status) from Subscription as u")
    List<Subscription> findAll();
}
