package com.customer.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for handling authentication with the Sunbase API.
 */
public class AuthAPIUtil {
    // URL for authentication endpoint
    private static final String AUTH_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    /**
     * Retrieves a bearer token from the authentication API using the provided login ID and password.
     * 
     * @param loginId  The login ID for authentication
     * @param password The password for authentication
     * @return The bearer token if authentication is successful, otherwise null
     */
    @SuppressWarnings("unchecked")
	public static String getBearerToken(String loginId, String password) {
        String token = null; // Initialize token variable
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Create a new POST request to the authentication URL
            HttpPost post = new HttpPost(AUTH_URL);
            post.setHeader("Content-Type", "application/json"); // Set content type header to JSON

            // Create JSON object for request body
            JSONObject json = new JSONObject();
            json.put("login_id", loginId); // Add login ID to JSON
            json.put("password", password); // Add password to JSON

            // Convert JSON object to string and set it as the request entity
            StringEntity entity = new StringEntity(json.toString());
            post.setEntity(entity);

            // Log the request details
            System.out.println("Executing request to " + AUTH_URL);
            System.out.println("Request JSON: " + json.toString());

            try (CloseableHttpResponse response = client.execute(post)) {
                // Log the response status
                System.out.println("Response status: " + response.getStatusLine());

                // Check if response status code is 200 (OK)
                if (response.getStatusLine().getStatusCode() == 200) {
                    // Convert response entity to string
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("Response JSON: " + result);

                    // Parse the response JSON to extract the token
                    JSONParser parser = new JSONParser();
                    JSONObject resultJson = (JSONObject) parser.parse(result);
                    token = (String) resultJson.get("access_token");
                } else {
                    // Log the failure reason if authentication fails
                    System.out.println("Failed to authenticate: " + response.getStatusLine().getReasonPhrase());
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
        return token; // Return the retrieved token or null if failed
    }

    /**
     * Main method for testing the authentication utility.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        String loginId = "test@sunbasedata.com"; // Test login ID
        String password = "Test@123"; // Test password
        String token = getBearerToken(loginId, password); // Get the bearer token

        // Log the result of the token retrieval
        if (token != null) {
            System.out.println("Generated Token: " + token);
        } else {
            System.out.println("Failed to generate token.");
        }
    }
}
