package com.customer.controller;

import com.customer.dao.CustomerDaoImpl;
import com.customer.model.Customer;
import com.customer.util.AuthAPIUtil;
import com.customer.util.CustomerAPIUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/syncCustomers")
public class SyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String LOGIN_ID = "test@sunbasedata.com"; // Your login ID
    private static final String PASSWORD = "Test@123"; // Your password

    private CustomerDaoImpl customerDao = new CustomerDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the token
        String token = AuthAPIUtil.getBearerToken(LOGIN_ID, PASSWORD);
        if (token == null) {
            response.getWriter().println("Failed to retrieve authentication token.");
            return;
        }

        // Retrieve the customer list
        JSONArray customerList = CustomerAPIUtil.getCustomerList(token);
        if (customerList == null || customerList.isEmpty()) {
            response.getWriter().println("No customers found or failed to retrieve customer list.");
            return;
        }

        // Sync customers with the local database
        for (Object customerObj : customerList) {
            JSONObject customerJson = (JSONObject) customerObj;

            String uuid = (String) customerJson.get("uuid");
            String firstName = (String) customerJson.get("first_name");
            String lastName = (String) customerJson.get("last_name");
            String street = (String) customerJson.get("street");
            String address = (String) customerJson.get("address");
            String city = (String) customerJson.get("city");
            String state = (String) customerJson.get("state");
            String email = (String) customerJson.get("email");
            String phone = (String) customerJson.get("phone");

            // Check if customer exists in the local database
            Customer existingCustomer = customerDao.findCustomerByUuid(uuid).stream().findFirst().orElse(null);

            if (existingCustomer == null) {
                // Add new customer
                Customer newCustomer = new Customer(uuid, firstName, lastName, street, address, city, state, email, phone);
                customerDao.addCustomer(newCustomer);
            } else {
                // Update existing customer
                existingCustomer.setFirstName(firstName);
                existingCustomer.setLastName(lastName);
                existingCustomer.setStreet(street);
                existingCustomer.setAddress(address);
                existingCustomer.setCity(city);
                existingCustomer.setState(state);
                existingCustomer.setPhone(phone);
                customerDao.updateCustomer(existingCustomer);
            }
        }

        response.getWriter().println("Customer data synchronized successfully.");
    }
}
