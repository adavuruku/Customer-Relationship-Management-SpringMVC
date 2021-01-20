package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; //inject the customer services here
	
	//@RequestMapping(path = "/list", method = RequestMethod.GET)
	//OR
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		// get customers from the service
				List<Customer> theCustomers = null;
				
				// check for sort field
				if (sort != null) {
					int theSortField = Integer.parseInt(sort);
					theCustomers = customerService.getCustomers(theSortField);			
				}
				else {
					// no sort field provided ... default to sorting by last name
					theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
				}
				
				// add the customers to the model
				theModel.addAttribute("customers", theCustomers);
				theModel.addAttribute("searchVal", "");
				return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer newCustomer = new Customer();
		
		theModel.addAttribute("customer", newCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer newCustomer) {
		
		customerService.saveCustomers(newCustomer);
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate( @RequestParam("customerId") int theId, Model theModel) {
		
		Customer newCustomer = customerService.getCustomer(theId);
		
		theModel.addAttribute("customer", newCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer( @RequestParam("customerId") int theId, Model theModel) {
		
		customerService.deleteCustomer(theId);
	
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        theModel.addAttribute("searchVal", theSearchName);
        return "list-customers";        
    }
}
