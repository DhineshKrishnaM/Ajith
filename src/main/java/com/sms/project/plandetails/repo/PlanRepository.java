package com.sms.project.plandetails.repo;

import com.sms.project.plandetails.entity.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository <PlanDetail,Integer> {

    @Query(value = "select New com.sms.project.plandetails.entity.PlanDetail(u.id,u.deleted_at,u.planName,u.planType,u.price) from PlanDetail as u")
    List<PlanDetail> findAll();


    @Query(value = "SELECT New com.sms.project.plandetails.entity.PlanDetail(u.id,u.deleted_at,u.planName,u.planType,u.price) FROM PlanDetail as u WHERE u.planType = :planType")
    List<PlanDetail> findAllByPlanType(@Param("planType") String planType);
}
