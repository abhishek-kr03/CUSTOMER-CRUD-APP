package com.customer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.customer.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
    private static final String URL = "jdbc:mysql://localhost:3306/customerdb"; // Database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "root"; // Database password
    private int noOfRecords; // Variable to store the number of records

    // Database connection variables
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    // SQL queries
    private String insertQuery = "INSERT INTO customers (uuid, first_name, last_name, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE customers SET uuid = ?, first_name = ?, last_name = ?, street = ?, address = ?, city = ?, state = ?, email = ?, phone = ? WHERE id = ?";
    private String deleteQuery = "DELETE FROM customers WHERE id = ?";
    private String selectQuery = "SELECT * FROM customers WHERE id = ?";
    private String listAllQuery = "SELECT * FROM customers ORDER BY %s %s LIMIT ?, ?";
    private String countQuery = "SELECT COUNT(*) FROM customers";
    private String findByUuidQuery = "SELECT * FROM customers WHERE uuid = ?";

    // Constructor to initialize the database connection
    public CustomerDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // Establish connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to list all customers with pagination and sorting
    @Override
    public List<Customer> listAllCustomers(int page, int pageSize, String sortField, String sortOrder) {
        List<Customer> customers = new ArrayList<>();
        String sql = String.format(listAllQuery, sortField, sortOrder); // Format the query with sort field and order

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * pageSize); // Set offset for pagination
            preparedStatement.setInt(2, pageSize); // Set limit for pagination

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = extractCustomerFromResultSet(resultSet); // Extract customer data from ResultSet
                customers.add(customer);
            }

            // Count the total number of records
            statement = connection.createStatement();
            resultSet = statement.executeQuery(countQuery);
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1); // Set the number of records
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers; // Return the list of customers
    }

    // Method to get the number of records
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    // Method to add a new customer
    @Override
    public void addCustomer(Customer customer) {
        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            // Set the parameters for the insert query
            preparedStatement.setString(1, customer.getUuid());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getStreet());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setString(7, customer.getState());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getPhone());
            preparedStatement.executeUpdate(); // Execute the query

            System.out.println("Customer added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    // Method to update an existing customer
    @Override
    public void updateCustomer(Customer customer) {
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            // Set the parameters for the update query
            preparedStatement.setString(1, customer.getUuid());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getStreet());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setString(7, customer.getState());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getPhone());
            preparedStatement.setInt(10, customer.getId());
            preparedStatement.executeUpdate(); // Execute the query

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    // Method to delete a customer by ID
    @Override
    public void deleteCustomer(int id) {
        try {
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id); // Set the ID parameter
            preparedStatement.executeUpdate(); // Execute the query

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a customer by ID
    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;

        try {
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id); // Set the ID parameter
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = extractCustomerFromResultSet(resultSet); // Extract customer data from ResultSet
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer; // Return the customer
    }

    // Method to find customers by UUID
    @Override
    public List<Customer> findCustomerByUuid(String uuid) {
        List<Customer> customers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(findByUuidQuery);
            preparedStatement.setString(1, uuid); // Set the UUID parameter
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = extractCustomerFromResultSet(resultSet); // Extract customer data from ResultSet
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers; // Return the list of customers
    }

    // Method to extract customer data from ResultSet
    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setUuid(resultSet.getString("uuid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setStreet(resultSet.getString("street"));
        customer.setAddress(resultSet.getString("address"));
        customer.setCity(resultSet.getString("city"));
        customer.setState(resultSet.getString("state"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPhone(resultSet.getString("phone"));
        return customer; // Return the customer
    }

    // Method to close all database resources
    public void close() {
        try {
            if (connection != null) {
                connection.close(); // Close the connection
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close(); // Close the PreparedStatement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close(); // Close the Statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null) {
                resultSet.close(); // Close the ResultSet
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
