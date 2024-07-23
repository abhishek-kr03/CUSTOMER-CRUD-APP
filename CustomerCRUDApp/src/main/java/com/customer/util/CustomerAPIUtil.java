package com.customer.util;

import java.io.StringReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for interacting with the Sunbase API to retrieve customer data.
 */
public class CustomerAPIUtil {
    // URL for retrieving customer data
    private static final String CUSTOMER_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    /**
     * Retrieves a list of customers from the Sunbase API using the provided bearer token.
     * 
     * @param token The bearer token for authorization
     * @return A JSONArray containing the list of customers if the request is successful, otherwise null
     */
    public static JSONArray getCustomerList(String token) {
        JSONArray customerList = null; // Initialize customer list variable
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Create a GET request to the customer API endpoint
            HttpGet get = new HttpGet(CUSTOMER_URL + "?cmd=get_customer_list");
            get.setHeader("Content-Type", "application/json"); // Set content type header to JSON
            get.setHeader("Authorization", "Bearer " + token); // Set authorization header with the bearer token

            // Log the request details
            System.out.println("Executing request to " + CUSTOMER_URL);
            System.out.println("Authorization: Bearer " + token);

            try (CloseableHttpResponse response = client.execute(get)) {
                // Log the response status
                System.out.println("Response status: " + response.getStatusLine());

                // Check if response status code is 200 (OK)
                if (response.getStatusLine().getStatusCode() == 200) {
                    // Convert response entity to string
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("Response JSON: " + result);

                    // Parse the response JSON to extract the customer list
                    JSONParser parser = new JSONParser();
                    customerList = (JSONArray) parser.parse(new StringReader(result));
                } else {
                    // Log the failure reason if the request fails
                    System.out.println("Failed to retrieve customer list: " + response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (ParseException e) {
            // Handle JSON parsing errors
            System.out.println("Error parsing JSON response");
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
        return customerList; // Return the retrieved customer list or null if failed
    }

    /**
     * Main method for testing the CustomerAPIUtil class.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Get the token using the AuthAPIUtil class
        String loginId = "test@sunbasedata.com"; // Test login ID
        String password = "Test@123"; // Test password
        String token = AuthAPIUtil.getBearerToken(loginId, password);

        if (token != null) {
            System.out.println("Generated Token: " + token);
            // Retrieve the list of customers using the token
            JSONArray customerList = getCustomerList(token);

            if (customerList != null && !customerList.isEmpty()) {
                // Iterate over the customer list and print customer details
                for (Object customerObj : customerList) {
                    JSONObject customer = (JSONObject) customerObj;
                    System.out.println("UUID: " + customer.get("uuid"));
                    System.out.println("First Name: " + customer.get("first_name"));
                    System.out.println("Last Name: " + customer.get("last_name"));
                    System.out.println("Street: " + customer.get("street"));
                    System.out.println("Address: " + customer.get("address"));
                    System.out.println("City: " + customer.get("city"));
                    System.out.println("State: " + customer.get("state"));
                    System.out.println("Email: " + customer.get("email"));
                    System.out.println("Phone: " + customer.get("phone"));
                    System.out.println(); // Adding a newline for better readability
                }
            } else {
                // Log if no customers are found
                System.out.println("No customers found.");
            }
        } else {
            // Log if the token generation fails
            System.out.println("Failed to generate token.");
        }
    }
}
