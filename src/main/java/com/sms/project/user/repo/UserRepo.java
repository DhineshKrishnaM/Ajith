package com.sms.project.user.repo;

import com.sms.project.plandetails.entity.PlanDetail;
import com.sms.project.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
//    @Query(value = "select New com.sms.project.user.entity.User(u.id,u.deleted_at,u.username,u.email,u.password,u.role,) from User as u")
    @Query(value = "select * from user",nativeQuery = true)
    List<User> findAll();
}
