package com.customer.dao;

import java.util.List;
import com.customer.model.Customer;

public interface CustomerDao {

    /**
     * Lists all customers with pagination and sorting options.
     * 
     * @param page the current page number
     * @param pageSize the number of records per page
     * @param sortField the field by which to sort the records
     * @param sortOrder the order of sorting (ASC/DESC)
     * @return a list of customers
     */
    public List<Customer> listAllCustomers(int page, int pageSize, String sortField, String sortOrder);
    
    /**
     * Adds a new customer to the database.
     * 
     * @param customer the customer to add
     */
    public void addCustomer(Customer customer);
    
    /**
     * Updates an existing customer in the database.
     * 
     * @param customer the customer to update
     */
    public void updateCustomer(Customer customer);
    
    /**
     * Retrieves a customer by their ID.
     * 
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     */
    public Customer getCustomerById(int id);
    
    /**
     * Finds customers by their UUID.
     * 
     * @param uuid the UUID of the customers to find
     * @return a list of customers with the specified UUID
     */
    public List<Customer> findCustomerByUuid(String uuid);
    
    /**
     * Deletes a customer from the database by their ID.
     * 
     * @param id the ID of the customer to delete
     */
    public void deleteCustomer(int id);
     
    /**
     * Retrieves the total number of records in the customer database.
     * 
     * @return the total number of records
     */
    int getNoOfRecords();
}
