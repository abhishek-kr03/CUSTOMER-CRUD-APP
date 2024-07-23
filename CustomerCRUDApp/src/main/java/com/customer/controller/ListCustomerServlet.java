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

    // Constructor initializes the CustomerDaoImpl instance
    public ListCustomerServlet() {
        this.customerDao = new CustomerDaoImpl();
    }

    /**
     * Handles the HTTP GET request to list customers with pagination and sorting.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get pagination and sorting parameters from the request
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        // Set default values if parameters are not provided
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = (pageSizeStr == null || pageSizeStr.isEmpty()) ? 10 : Integer.parseInt(pageSizeStr);
        sortField = (sortField == null || sortField.isEmpty()) ? "id" : sortField;
        sortOrder = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder;

        // Retrieve the list of customers based on the given pagination and sorting parameters
        List<Customer> customers = customerDao.listAllCustomers(page, pageSize, sortField, sortOrder);
        
        // Get the total number of records to calculate the number of pages
        int noOfRecords = customerDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / pageSize);

        // Set attributes to be accessed in the JSP
        request.setAttribute("customers", customers);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("sortField", sortField);
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("noOfPages", noOfPages);

        // Forward the request to the JSP page for rendering the customer list
        request.getRequestDispatcher("listCustomers.jsp").forward(request, response);
    }
}
