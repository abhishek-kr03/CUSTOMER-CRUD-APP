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

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Id id: " + id);
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        customerDao.deleteCustomer(id);
        response.sendRedirect("listCustomers");
    }
}
