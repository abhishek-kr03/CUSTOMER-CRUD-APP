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

public class CustomerAPIUtil {
    private static final String CUSTOMER_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    public static JSONArray getCustomerList(String token) {
        JSONArray customerList = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(CUSTOMER_URL + "?cmd=get_customer_list");
            get.setHeader("Content-Type", "application/json");
            get.setHeader("Authorization", "Bearer " + token);

            // Logging request
            System.out.println("Executing request to " + CUSTOMER_URL);
            System.out.println("Authorization: Bearer " + token);

            try (CloseableHttpResponse response = client.execute(get)) {
                // Logging response status
                System.out.println("Response status: " + response.getStatusLine());

                if (response.getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("Response JSON: " + result);

                    JSONParser parser = new JSONParser();
                    customerList = (JSONArray) parser.parse(new StringReader(result));
                } else {
                    System.out.println("Failed to retrieve customer list: " + response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static void main(String[] args) {
        // Get the token using the AuthAPIUtil
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";
        String token = AuthAPIUtil.getBearerToken(loginId, password);

        if (token != null) {
            System.out.println("Generated Token: " + token);
            JSONArray customerList = getCustomerList(token);

            if (customerList != null && !customerList.isEmpty()) {
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
                System.out.println("No customers found.");
            }
        } else {
            System.out.println("Failed to generate token.");
        }
    }
}
