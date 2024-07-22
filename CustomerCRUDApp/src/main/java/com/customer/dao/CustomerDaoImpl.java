package com.customer.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.customer.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
    private static final String URL = "jdbc:mysql://localhost:3306/customerdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private int noOfRecords;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Customer> listAllCustomers(int page, int pageSize, String sortField, String sortOrder) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY " + sortField + " " + sortOrder +
                     " LIMIT ?, ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, (page - 1) * pageSize);
            pstmt.setInt(2, pageSize);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setUuid(rs.getString("uuid"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setStreet(rs.getString("street"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setState(rs.getString("state"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    customers.add(customer);
                }
            }

            // Count the total number of records
            String countSql = "SELECT COUNT(*) FROM customers";
            try (Statement countStmt = conn.createStatement();
                 ResultSet countRs = countStmt.executeQuery(countSql)) {

                if (countRs.next()) {
                    this.noOfRecords = countRs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    // other methods (addCustomer, updateCustomer, deleteCustomer, getCustomerById) remain unchanged

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (uuid, first_name, last_name, street, address, city, state, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getUuid());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getStreet());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getCity());
            pstmt.setString(7, customer.getState());
            pstmt.setString(8, customer.getEmail());
            pstmt.setString(9, customer.getPhone());
            pstmt.executeUpdate();

            System.out.println("Customer added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET uuid = ?, first_name = ?, last_name = ?, street = ?, address = ?, city = ?, state = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getUuid());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getStreet());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getCity());
            pstmt.setString(7, customer.getState());
            pstmt.setString(8, customer.getEmail());
            pstmt.setString(9, customer.getPhone());
            pstmt.setInt(10, customer.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUuid(rs.getString("uuid"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setStreet(rs.getString("street"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
 // In CustomerDaoImpl.java
    public List<Customer> findCustomerByUuid(String uuid) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE uuid = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, uuid);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


}