package com.luv2code.springdemo.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

/*
 * We create service to implement the data access objects (dao)
 * this will enhance data organization and allow injecting or using many dao in one
 * services 
 * Note: the DAO still have the imlementation you are just using services to consume them
 * in essence a single service can consume from different dao. so basically the controler only
 * access the services not the DAOS
 * */



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired //this inject the customer dao into this service
	private CustomerDAO customerDAO;
	
	
	// @Transactional //when this is added u dont need to start and end transaction manually
	@Transactional
	@Override
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}

	
	@Transactional
	@Override
	public void saveCustomers(Customer newCustomer) {
		customerDAO.saveCustomer(newCustomer);
	}


	@Transactional
	@Override
	public Customer getCustomer(int theId) {
		return customerDAO.getCustomer(theId);
	}


	@Transactional
	@Override
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);
	}


	@Transactional
	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		return customerDAO.searchCustomers(theSearchName);
	}
	
}
