package com.group6.MoM.service;

import com.group6.MoM.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.MoM.repository.DriverRepository;
import com.group6.MoM.repository.MemberRepository;
import com.group6.MoM.repository.MenusRepository;
import com.group6.MoM.repository.OrderRepository;
import com.group6.MoM.repository.PartnerRepository;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	private UsersService usersService;
	
	@Autowired
	private DriverRepository dvr;
	
	public void newOrder(int menuId, int memberId) {
		OrderMenu orderMenu = new OrderMenu();

		Menus menu = menusRepo.getById(menuId);

		orderMenu.setMenu(menu);
		orderMenu.setMember(mr.getById(memberId));
		orderMenu.setPartnerName(menu.getPartner().getName());
		orderMenu.setPartnerId(menu.getPartner().getPartnerId());
		orderMenu.setStatus("ORDERING");
		orderRepo.save(orderMenu);
	}
	
	public void changeStatusOrder(String status, int orderId, int driverId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		Driver driver = dvr.findById(driverId).get();
		orderMenu.setDriver(driver);
		orderMenu.setStatus(status);
		orderRepo.save(orderMenu);
	}
	
	public void prosesOrder(String status, int orderId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		orderMenu.setStatus(status);
		orderMenu.setPartnerId(orderMenu.getPartnerId());
		orderRepo.save(orderMenu);
	}
	
	public void deliveredOrder(String status, int orderId) {
		OrderMenu orderMenu = orderRepo.getById(orderId);
		orderMenu.setStatus(status);
		orderRepo.save(orderMenu);
	}

	public List<OrderMenu> findAllOrderFromStatus(Set<String> statuses) {
		List<OrderMenu> listOrderMenu = orderRepo.findAll();

		return listOrderMenu.stream()
				.filter(order -> statuses.contains(order.getStatus()))
				.collect(Collectors.toList());

	}


	public List<OrderMenu> findAllOrderByPartnerId(int partnerId) {

		List<OrderMenu> listOrderMenu = orderRepo.findByMember(partnerId);
		return listOrderMenu.stream()
				.filter(order -> order.getStatus().equals("PROCESSING"))
				.collect(Collectors.toList());
	}
}