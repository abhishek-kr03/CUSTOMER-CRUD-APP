<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Customer</title>
    <link rel="stylesheet" href="styles.css">
    
    <style type="text/css">
    	.form-actions {
		  
		    justify-content: center;
		    margin-top: 20px;
		}
		
		.form-actions input[type="submit"],
		.form-actions button {
		    padding: 10px 20px;
		    border: none;
		    border-radius: 5px;
		    background-color: #555;
		    color: #fff;
		    cursor: pointer;
		    margin-left: 10px;
		}
		
		.form-actions input[type="submit"]:hover,
		.form-actions button:hover {
		    background-color: #666;
		}
    </style>
    
    
</head>
<body>
    <header>
        <h1>Update Customer</h1>
    </header>
    <main>
        <form action="updateCustomer" method="post" class="update-form">
            <input type="hidden" name="id" value="${customer.id}">
            <table>
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" id="firstName" name="firstName" value="${customer.firstName}" required></td>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" id="lastName" name="lastName" value="${customer.lastName}" required></td>
                </tr>
                <tr>
                    <td><label for="street">Street:</label></td>
                    <td><input type="text" id="street" name="street" value="${customer.street}" required></td>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" id="address" name="address" value="${customer.address}" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" id="city" name="city" value="${customer.city}" required></td>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" id="state" name="state" value="${customer.state}" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" id="email" name="email" value="${customer.email}" required></td>
                    <td><label for="phone">Phone:</label></td>
                    <td><input type="text" id="phone" name="phone" value="${customer.phone}" required></td>
                </tr>
            </table>
            <div class="form-actions">
                <input type="submit" value="Update">
                <button type="button" onclick="window.location.href='listCustomers'">Cancel</button>
            </div>
        </form>
    </main>
</body>
</html>
