package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository //is applied to only dao - @component - is class - @controller for controllers
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	//using autowired since factory is defined in spring-mvc-crud-demo-servlet.xml
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {	
//		Session currentSession =  HibernateUtil.getSessionFactory().openSession();
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query  ... sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName, lastName",
											Customer.class);
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the customer ... finally LOL
//		currentSession.save(theCustomer);
		
		//we are using saveORupdate here because we are using thesame method
		//for updating user 
		//since u are updating make sure u send the object Id from the form
		//as well
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
	
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		//delete the customer
		currentSession.delete(theCustomer);
		
//		Query theQuery = currentSession.createQuery("delete from Customer where id = :customerId");
//		theQuery.setParameter("customerId", theId);
//		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// create a query  to search database
			theQuery = 
					currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
												Customer.class);
			
			theQuery.setParameter("theName", "%"+theSearchName.toLowerCase() + "%");
			
			
		}else {
			theQuery =currentSession.createQuery("from Customer order by firstName, lastName", Customer.class);
		}
			// execute query and get result list
			List<Customer> customers = theQuery.getResultList();
			// return the results		
			return customers;
	}

}






