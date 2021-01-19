package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDAO CustomerDAO;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> allCustomers = CustomerDAO.getCustomers();
		
		theModel.addAttribute("customers", allCustomers);
		
		return "list-customers";
	}
}
