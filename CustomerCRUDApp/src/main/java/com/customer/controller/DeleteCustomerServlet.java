package com.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.customer.dao.CustomerDaoImpl;

@WebServlet("/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to delete a customer by ID.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse the customer ID from the request parameter
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Log the customer ID for debugging purposes
        System.out.println("Customer ID: " + id);
        
        // Create an instance of CustomerDaoImpl to interact with the database
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        
        // Delete the customer from the database
        customerDao.deleteCustomer(id);
        
        // Redirect the response to the listCustomers page
        response.sendRedirect("listCustomers");
    }
}
