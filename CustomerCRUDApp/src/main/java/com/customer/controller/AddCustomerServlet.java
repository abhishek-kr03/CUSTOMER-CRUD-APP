package com.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.dao.CustomerDaoImpl;
import com.customer.model.Customer;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setStreet(request.getParameter("street"));
        customer.setAddress(request.getParameter("address"));
        customer.setCity(request.getParameter("city"));
        customer.setState(request.getParameter("state"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhone(request.getParameter("phone"));

        customerDao.addCustomer(customer);

        response.sendRedirect("listCustomers");
    }
}
