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

public class AuthAPIUtil {
    private static final String AUTH_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    public static String getBearerToken(String loginId, String password) {
        String token = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(AUTH_URL);
            post.setHeader("Content-Type", "application/json");

            // Create JSON request body
            JSONObject json = new JSONObject();
            json.put("login_id", loginId);
            json.put("password", password);

            // Set JSON entity
            StringEntity entity = new StringEntity(json.toString());
            post.setEntity(entity);

            // Logging request
            System.out.println("Executing request to " + AUTH_URL);
            System.out.println("Request JSON: " + json.toString());

            try (CloseableHttpResponse response = client.execute(post)) {
                // Logging response status
                System.out.println("Response status: " + response.getStatusLine());

                if (response.getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("Response JSON: " + result);

                    JSONParser parser = new JSONParser();
                    JSONObject resultJson = (JSONObject) parser.parse(result);
                    token = (String) resultJson.get("access_token");
                } else {
                    System.out.println("Failed to authenticate: " + response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static void main(String[] args) {
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";
        String token = getBearerToken(loginId, password);

        if (token != null) {
            System.out.println("Generated Token: " + token);
        } else {
            System.out.println("Failed to generate token.");
        }
    }
}
