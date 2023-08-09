package com.group6.MoM.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.group6.MoM.dto.DeliveryDto;
import com.group6.MoM.entity.Driver;
import com.group6.MoM.entity.Member;
import com.group6.MoM.entity.User;
import com.group6.MoM.repository.MemberRepository;
import com.group6.MoM.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group6.MoM.entity.OrderMenu;
import com.group6.MoM.repository.OrderRepository;
import com.group6.MoM.service.OrderMenuService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderMenuService oms;
	
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderMenuService orderMenuService;

	@Autowired
	private UsersService usersService;
	@Autowired
	private MemberRepository memberRepo;
	
	@PostMapping("/new_order")
	public void newOrder(@RequestBody Map<String, Object> requestData) {
	    int menuId = (int) requestData.get("menuId");
	    int memberId = (int) requestData.get("memberId");



	    oms.newOrder(menuId, memberId);
	}
	@GetMapping("/list_order")
	public List<OrderMenu> listAllOrders() {
		return orderRepo.findAll();
	}

	@GetMapping("/list_driver_order")
	public List<OrderMenu> listDriverOrder() {

		Set<String> statuses = new HashSet<>();
		statuses.add("PROCESSING");
		statuses.add("DELIVERING");


		return orderMenuService.findAllOrderFromStatus(statuses);
	}

	@GetMapping("/list_partner_order")
	public List<OrderMenu> listPartnerOrder() {

		Set<String> statuses = new HashSet<>();
		statuses.add("ORDERING");

		return orderMenuService.findAllOrderFromStatus(statuses);
	}

	@GetMapping("/list_order/{id}")
	public List<OrderMenu> listOrder(@PathVariable("id")Integer id){
		Member member = memberRepo.findById(id).get();
		return orderRepo.findByMember(member);
	}
	
	@PostMapping("/proses_order/{order_id}")
	public void prosesOrder(@PathVariable("order_id") int orderId) {
		oms.prosesOrder("PROCESSING", orderId);
	}
	
	@PostMapping("/ready_order")
	public void readyOrder(@RequestBody DeliveryDto requestData) {
		oms.changeStatusOrder("DELIVERING", requestData.getOrderId(), requestData.getDriverId());
	}
	@PostMapping("/ready_order_volunteer")
	public void readyOrderVolunteer(@RequestBody DeliveryDto requestData) {
		oms.changeStatusVolunteer("DELIVERING", requestData.getOrderId(), requestData.getVolunteerId());
	}
	@PostMapping("/delivered_order/{order_id}")
	public void deliveredOrder(@PathVariable("order_id") int orderId) {
	    oms.deliveredOrder("DELIVERED", orderId);
	}

	@PostMapping("/done_order/{order_id}")
	public void doneOrder(@PathVariable("order_id") int orderId) {
		oms.deliveredOrder("DONE", orderId);
	}
	
}
 