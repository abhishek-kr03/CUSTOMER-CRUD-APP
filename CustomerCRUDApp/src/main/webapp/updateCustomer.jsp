<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Title of the page displayed in the browser tab -->
    <title>Update Customer</title>
    
    <!-- Link to external CSS file for styling -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Header section of the page -->
    <header>
        <h1>Update Customer</h1>
    </header>
    
    <!-- Main content area -->
    <main>
        <!-- Form for updating customer information -->
        <form action="updateCustomer" method="post" class="update-form">
            <!-- Hidden field to store the customer ID -->
            <input type="hidden" name="id" value="${customer.id}">
            
            <!-- Table for organizing form fields -->
            <table>
                <!-- Row for first name and last name -->
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <!-- Input field for first name, pre-filled with current value -->
                    <td><input type="text" id="firstName" name="firstName" value="${customer.firstName}" required></td>
                    
                    <td><label for="lastName">Last Name:</label></td>
                    <!-- Input field for last name, pre-filled with current value -->
                    <td><input type="text" id="lastName" name="lastName" value="${customer.lastName}" required></td>
                </tr>
                
                <!-- Row for street and address -->
                <tr>
                    <td><label for="street">Street:</label></td>
                    <!-- Input field for street, pre-filled with current value -->
                    <td><input type="text" id="street" name="street" value="${customer.street}" required></td>
                    
                    <td><label for="address">Address:</label></td>
                    <!-- Input field for address, pre-filled with current value -->
                    <td><input type="text" id="address" name="address" value="${customer.address}" required></td>
                </tr>
                
                <!-- Row for city and state -->
                <tr>
                    <td><label for="city">City:</label></td>
                    <!-- Input field for city, pre-filled with current value -->
                    <td><input type="text" id="city" name="city" value="${customer.city}" required></td>
                    
                    <td><label for="state">State:</label></td>
                    <!-- Input field for state, pre-filled with current value -->
                    <td><input type="text" id="state" name="state" value="${customer.state}" required></td>
                </tr>
                
                <!-- Row for email and phone -->
                <tr>
                    <td><label for="email">Email:</label></td>
                    <!-- Input field for email, pre-filled with current value -->
                    <td><input type="email" id="email" name="email" value="${customer.email}" required></td>
                    
                    <td><label for="phone">Phone:</label></td>
                    <!-- Input field for phone, pre-filled with current value -->
                    <td><input type="text" id="phone" name="phone" value="${customer.phone}" required></td>
                </tr>
            </table>
            
            <!-- Form action buttons -->
            <div class="form-actions">
                <!-- Submit button to save changes -->
                <input type="submit" value="Update">
                
                <!-- Cancel button to return to the customer list -->
                <button type="button" onclick="window.location.href='listCustomers'">Cancel</button>
            </div>
        </form>
    </main>
</body>
</html>
