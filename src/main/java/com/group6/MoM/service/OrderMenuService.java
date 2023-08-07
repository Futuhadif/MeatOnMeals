package com.group6.MoM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.MoM.entity.OrderMenu;
import com.group6.MoM.entity.Partner;
import com.group6.MoM.repository.DriverRepository;
import com.group6.MoM.repository.MemberRepository;
import com.group6.MoM.repository.MenusRepository;
import com.group6.MoM.repository.OrderRepository;
import com.group6.MoM.repository.PartnerRepository;

@Service
public class OrderMenuService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MenusRepository menusRepo;
	
	@Autowired
	private MemberRepository mr;
	
	@Autowired
	private PartnerRepository pr;
	
	@Autowired
	private DriverRepository dvr;
	
	public void newOrder(int menuId, int memberId) {
		OrderMenu orderMenu = new OrderMenu();
		
		orderMenu.setMenu(menusRepo.getById(menuId));
		orderMenu.setMember(mr.getById(memberId));
		orderMenu.setStatus("ORDERING");
		orderRepo.save(orderMenu);
	}
	
	public void changeStatusOrder(String status, int orderId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		
		orderMenu.setStatus(status);
		orderRepo.save(orderMenu);
	}
	
	public void prosesOrder(String status, int orderId, int partnerId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		Partner partner = pr.getById(partnerId);
		orderMenu.setStatus(status);
		orderMenu.setPartnerName(partner.getName());
		orderRepo.save(orderMenu);
	}
	
	public void deliveredOrder(int orderId, int driverId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		
		orderMenu.setStatus("delivered");
		orderMenu.setDriver(dvr.getById(driverId));
		orderRepo.save(orderMenu);
	}
}