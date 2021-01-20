package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(int theSortField);
	
	public void saveCustomers(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);
}
