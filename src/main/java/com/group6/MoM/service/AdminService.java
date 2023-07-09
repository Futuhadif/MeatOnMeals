package com.group6.MoM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.MoM.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository ur;
	
	
	public void setApproveal(int userId, boolean status) {
		ur.updateApprovedStatus(userId, status);
	}
	
	
	
	
}
