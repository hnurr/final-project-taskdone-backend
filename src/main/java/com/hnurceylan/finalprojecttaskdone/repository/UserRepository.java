package com.hnurceylan.finalprojecttaskdone.repository;

import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAllByIsApprovedFalseAndRole(Role role);

    // Sadece provider ve onaylı olan kullanıcıları getiren sorgu
    @Query("SELECT u FROM User u WHERE u.role = 'provider' AND u.isApproved = true")
    List<User> findByRoleAndStatus();



}
