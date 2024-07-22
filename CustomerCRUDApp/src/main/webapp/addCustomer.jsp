<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Customer</title>
    <link rel="stylesheet" href="styles.css">
    
</head>
<body>
    <header>
        <h1>Add New Customer</h1>
    </header>
    <main>
        <form action="addCustomer" method="post" class="update-form">
            <input type="hidden" name="id" value="${customer.id}">
            <table>
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" id="firstName" name="firstName"  required></td>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" id="lastName" name="lastName"  required></td>
                </tr>
                <tr>
                    <td><label for="street">Street:</label></td>
                    <td><input type="text" id="street" name="street"  required></td>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" id="city" name="city" required></td>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" id="email" name="email"  required></td>
                    <td><label for="phone">Phone:</label></td>
                    <td><input type="text" id="phone" name="phone"  required></td>
                </tr>
            </table>
            <div class="form-actions">
                <button type="submit">Save</button>
            </div>
        </form>
    </main>
</body>
</html>
