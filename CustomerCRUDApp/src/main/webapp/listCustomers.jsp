<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.customer.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Title of the page displayed in the browser tab -->
    <title>Customer List</title>
    
    <!-- Link to external CSS file for styling -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Header section of the page -->
    <header>
        <h1>Customer List</h1>
        <div class="header-actions">
            <!-- Button to navigate to the Add Customer page -->
            <button onclick="window.location.href='addCustomer.jsp'">Add Customer</button>

            <!-- Dropdown menu for search criteria -->
            <div class="dropdown">
                <button class="dropbtn">Search by</button>
                <div class="dropdown-content">
                    <a href="#" onclick="setSearchBy('firstName')">First Name</a>
                    <a href="#" onclick="setSearchBy('city')">City</a>
                    <a href="#" onclick="setSearchBy('email')">Email</a>
                    <a href="#" onclick="setSearchBy('phone')">Phone</a>
                </div>
            </div>

            <!-- Input field for searching customers -->
            <input type="text" id="searchInput" placeholder="Search..." onkeyup="searchCustomer()">

            <!-- Button to trigger synchronization of customers -->
            <button onclick="syncCustomers()">Sync</button>
        </div>
    </header>

    <!-- Main content area -->
    <main>
        <!-- Table to display customer data -->
        <table border="1">
            <thead>
                <tr>
                    <!-- Table headers -->
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Street</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="customerTableBody">
                <!-- Server-side code to populate table rows with customer data -->
                <%
                	@SuppressWarnings("unchecked")
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    for (Customer customer : customers) {
                %>
                    <tr>
                        <td><%= customer.getFirstName() %></td>
                        <td><%= customer.getLastName() %></td>
                        <td><%= customer.getStreet() %></td>
                        <td><%= customer.getAddress() %></td>
                        <td><%= customer.getCity() %></td>
                        <td><%= customer.getState() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getPhone() %></td>
                        <td class="action-buttons">
                            <!-- Buttons for editing and deleting customers -->
                            <img src="images/edit.png" height="30px" width="30px" alt="Edit" title="Edit this customer" onclick="editCustomer('<%= customer.getId() %>')">
                            <img src="images/delete.png" height="30px" width="30px" alt="Delete" title="Delete this customer" onclick="deleteCustomer('<%= customer.getId() %>')">
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Pagination controls -->
        <div class="pagination">
            <%
                int noOfPages = (Integer) request.getAttribute("noOfPages");
                int currentPage = (Integer) request.getAttribute("currentPage");
    
                // Previous page button
                if (currentPage > 1) {
                    out.print("<a class='pagination-btn' href='listCustomers?page=" + (currentPage - 1) + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>Previous</a> ");
                }
    
                // Page number buttons
                for (int i = 1; i <= noOfPages; i++) {
                    if (i == currentPage) {
                        out.print("<span class='pagination-btn current'>" + i + "</span> ");
                    } else {
                        out.print("<a class='pagination-btn' href='listCustomers?page=" + i + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>" + i + "</a> ");
                    }
                }
    
                // Next page button
                if (currentPage < noOfPages) {
                    out.print("<a class='pagination-btn' href='listCustomers?page=" + (currentPage + 1) + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>Next</a>");
                }
            %>
        </div>
    </main>

    <!-- JavaScript for dynamic functionality -->
    <script>
    let searchBy = 'firstName'; // Default search criteria

    // Set the search criteria
    function setSearchBy(criteria) {
        searchBy = criteria;
    }

    // Function to filter table rows based on search input
    function searchCustomer() {
        const input = document.getElementById('searchInput').value.toLowerCase();
        const table = document.getElementById('customerTableBody');
        const rows = table.getElementsByTagName('tr');

        let columnIndex;
        // Determine the column index based on search criteria
        switch (searchBy) {
            case 'firstName':
                columnIndex = 0;
                break;
            case 'city':
                columnIndex = 4;
                break;
            case 'email':
                columnIndex = 6;
                break;
            case 'phone':
                columnIndex = 7;
                break;
            default:
                columnIndex = 0;
        }

        // Loop through table rows and hide/show based on search input
        for (let i = 0; i < rows.length; i++) {
            const cell = rows[i].getElementsByTagName('td')[columnIndex];
            if (cell) {
                const txtValue = cell.textContent || cell.innerText;
                if (txtValue.toLowerCase().indexOf(input) > -1) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }       
        }
    }

    // Function to trigger synchronization of customers
    function syncCustomers() {
        window.location.href = 'syncCustomers';
    }

    // Function to navigate to the edit customer page
    function editCustomer(id) {
        window.location.href = 'updateCustomer?id=' + id;
    }

    // Function to confirm and navigate to the delete customer action
    function deleteCustomer(id) {
        if (confirm('Are you sure you want to delete this customer?')) {
            window.location.href = 'deleteCustomer?id=' + id;
        }
    }
    </script>
</body>
</html>
