package com.group6.MoM.repository;

import com.group6.MoM.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.OrderMenu;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderMenu, Integer>{


    List<OrderMenu>findByMember(Member member);


}
