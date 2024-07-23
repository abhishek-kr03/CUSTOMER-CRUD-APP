package com.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.dao.CustomerDaoImpl;
import com.customer.model.Customer;

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to display the customer update form.
     * Retrieves customer details from the database and forwards them to the updateCustomer.jsp page.
     *
     * @param request  HttpServletRequest object containing request details
     * @param response HttpServletResponse object used to send a response to the client
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve customer ID from the request parameter
        int customerId = Integer.parseInt(request.getParameter("id"));
        
        // Create an instance of CustomerDaoImpl to interact with the database
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        
        // Get customer details by ID
        Customer customer = customerDao.getCustomerById(customerId);
        
        // Set the customer object as a request attribute for use in the JSP page
        request.setAttribute("customer", customer);
        
        // Forward the request to the updateCustomer.jsp page to display the update form
        request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST request to update customer details in the database.
     * Retrieves updated customer details from the request, updates the customer in the database,
     * and then redirects to the listCustomers page.
     *
     * @param request  HttpServletRequest object containing request details
     * @param response HttpServletResponse object used to send a response to the client
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create an instance of CustomerDaoImpl to interact with the database
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        
        // Create a new Customer object and set its properties from the request parameters
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(request.getParameter("id")));
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setStreet(request.getParameter("street"));
        customer.setAddress(request.getParameter("address"));
        customer.setCity(request.getParameter("city"));
        customer.setState(request.getParameter("state"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhone(request.getParameter("phone"));

        // Update the customer details in the database
        customerDao.updateCustomer(customer);

        // Redirect to the listCustomers page after updating the customer
        response.sendRedirect("listCustomers");
    }
}
