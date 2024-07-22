package com.customer.dao;

import java.util.List;

import com.customer.model.Customer;

public interface CustomerDao {

	public List<Customer> listAllCustomers(int page, int pageSize, String sortField, String sortOrder);
	
	public void addCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	public Customer getCustomerById(int id);
	
	public List<Customer> findCustomerByUuid(String uuid);
	 
	 
}
