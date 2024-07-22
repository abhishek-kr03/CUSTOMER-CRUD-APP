package com.customer.controller;

import com.customer.dao.CustomerDaoImpl;
import com.customer.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listCustomers")
public class ListCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDaoImpl customerDao;

    public ListCustomerServlet() {
        this.customerDao = new CustomerDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = (pageSizeStr == null || pageSizeStr.isEmpty()) ? 10 : Integer.parseInt(pageSizeStr);
        sortField = (sortField == null || sortField.isEmpty()) ? "id" : sortField;
        sortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        List<Customer> customers = customerDao.listAllCustomers(page, pageSize, sortField, sortOrder);
        int noOfRecords = customerDao.getNoOfRecords(); // Ensure you have this method implemented correctly
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / pageSize);

        request.setAttribute("customers", customers);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("sortField", sortField);
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("noOfPages", noOfPages);

        request.getRequestDispatcher("listCustomers.jsp").forward(request, response);
    }
}
