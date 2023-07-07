package com.group6.MoM.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);

	public User findByUsername(String username);

	@Modifying
    @Query("UPDATE User u SET u.approved = :approved WHERE u.userId = :userId")
    void updateApprovedStatus(int userId, boolean approved);
	
	
	
}
