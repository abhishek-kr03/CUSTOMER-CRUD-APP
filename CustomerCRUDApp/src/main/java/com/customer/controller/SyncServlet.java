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

    // Credentials for accessing the Sunbase API
    private static final String LOGIN_ID = "test@sunbasedata.com"; // Your login ID
    private static final String PASSWORD = "Test@123"; // Your password

    // DAO instance for interacting with the local customer database
    private CustomerDaoImpl customerDao = new CustomerDaoImpl();

    /**
     * Handles the HTTP GET request to synchronize customer data from an external API to the local database.
     *
     * @param request  HttpServletRequest object containing request details
     * @param response HttpServletResponse object used to send a response to the client
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the authentication token from the API
        String token = AuthAPIUtil.getBearerToken(LOGIN_ID, PASSWORD);
        if (token == null) {
            response.getWriter().println("Failed to retrieve authentication token.");
            return;
        }

        // Retrieve the list of customers from the external API using the token
        JSONArray customerList = CustomerAPIUtil.getCustomerList(token);
        if (customerList == null || customerList.isEmpty()) {
            response.getWriter().println("No customers found or failed to retrieve customer list.");
            return;
        }

        // Iterate over each customer object retrieved from the API
        for (Object customerObj : customerList) {
            // Convert the customer object from JSON format to a JSONObject
            JSONObject customerJson = (JSONObject) customerObj;

            // Extract customer details from the JSON object
            String uuid = (String) customerJson.get("uuid");
            String firstName = (String) customerJson.get("first_name");
            String lastName = (String) customerJson.get("last_name");
            String street = (String) customerJson.get("street");
            String address = (String) customerJson.get("address");
            String city = (String) customerJson.get("city");
            String state = (String) customerJson.get("state");
            String email = (String) customerJson.get("email");
            String phone = (String) customerJson.get("phone");

            // Check if the customer already exists in the local database using the UUID
            Customer existingCustomer = customerDao.findCustomerByUuid(uuid).stream().findFirst().orElse(null);

            if (existingCustomer == null) {
                // If the customer does not exist, create a new Customer object and add it to the database
                Customer newCustomer = new Customer(uuid, firstName, lastName, street, address, city, state, email, phone);
                customerDao.addCustomer(newCustomer);
            } else {
                // If the customer exists, update their details in the local database
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

        // Send a response indicating that the customer data has been synchronized successfully
        response.getWriter().println("Customer data synchronized successfully.");
    }
}
