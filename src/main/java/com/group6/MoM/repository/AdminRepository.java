package com.group6.MoM.repository;

import com.group6.MoM.entity.Donatur;
import com.group6.MoM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.Admin;
import com.group6.MoM.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
<<<<<<< Updated upstream

	Admin findByUser(User user);
	
=======
>>>>>>> Stashed changes

}
