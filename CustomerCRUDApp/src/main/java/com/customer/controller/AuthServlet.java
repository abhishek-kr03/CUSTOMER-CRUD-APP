package com.customer.controller;

import com.customer.util.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")  // Maps HTTP requests to /login to this servlet
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 6062894120451688816L; // Serial version UID for serialization

    /**
     * Handles POST requests for user authentication.
     *
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the POST request.
     * @throws IOException      If an input or output error occurs during the handling of the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve loginId and password parameters from the request
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        // Debugging output: Print loginId and password to the server console (not for production use)
        System.out.println("Login ID: " + loginId);
        System.out.println("Password: " + password);

        // Simple hardcoded authentication logic for demonstration purposes
        if ("test@sunbasedata.com".equals(loginId) && "Test@123".equals(password)) {
            // Generate a JWT token for the authenticated user
            String token = JWTUtil.generateToken(loginId);
            
            // Set the JWT token in the response header
            response.setHeader("Authorization", "Bearer " + token);
            
            // Redirect to the listCustomers servlet/page upon successful authentication
            response.sendRedirect("listCustomers");
        } else {
            // Log an invalid credentials message to the server console
            System.out.println("Invalid credentials");
            
            // Redirect to the login page if authentication fails
            response.sendRedirect("login.jsp");
        }
    }
}
