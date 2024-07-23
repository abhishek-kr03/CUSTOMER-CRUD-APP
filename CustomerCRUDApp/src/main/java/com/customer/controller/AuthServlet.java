package com.customer.controller;

import com.customer.util.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 6062894120451688816L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
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

        // Validate credentials using the method defined below
        if (validateCredentials(loginId, password)) {
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

    /**
     * Validates the login credentials against the admin table in the database.
     *
     * @param email    The email address provided by the user.
     * @param password The password provided by the user.
     * @return true if the credentials are valid, false otherwise.
     */
    private boolean validateCredentials(String email, String password) {
        boolean isValid = false;
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the statement
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if credentials are valid
            isValid = resultSet.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
}
