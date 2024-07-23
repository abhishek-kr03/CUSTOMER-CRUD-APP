package com.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.dao.CustomerDaoImpl;
import com.customer.model.Customer;

@WebServlet("/selectCustomer")
public class SelectCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to retrieve and display customer details.
     *
     * @param request  HttpServletRequest object containing request details
     * @param response HttpServletResponse object used to send a response to the client
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract the customer ID from the request parameter
        // Converts the 'id' parameter from String to int
        int id = Integer.parseInt(request.getParameter("id"));

        // Instantiate the CustomerDaoImpl class to perform database operations
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        // Retrieve customer details from the database using the provided ID
        Customer customer = customerDao.getCustomerById(id);

        // Set the customer object as a request attribute for the JSP page to access
        request.setAttribute("customer", customer);

        // Forward the request and response to the addCustomer.jsp page to display customer details
        // The JSP page will use the 'customer' attribute to populate the form fields
        request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
    }
}
