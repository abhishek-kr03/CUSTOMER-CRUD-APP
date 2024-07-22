<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.customer.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        .pagination {
            text-align: center;
            margin: 20px 0;
        }
        
        .pagination a,
        .pagination span {
            display: inline-block;
            padding: 10px 15px;
            margin: 0 5px;
            border-radius: 5px;
            text-decoration: none;
            color: #fff;
            background-color: #555;
            transition: background-color 0.3s ease;
        }
        
        .pagination a:hover {
            background-color: #666;
        }
        
        .pagination .current {
            background-color: #666;
            cursor: default;
        }
        
        .pagination .pagination-btn {
            cursor: pointer;
        }
        
        .pagination .pagination-btn {
            font-weight: bold;
        }               
    </style>
</head>
<body>
    <header>
        <h1>Customer List</h1>
        <div class="header-actions">
            <button onclick="window.location.href='addCustomer.jsp'">Add Customer</button>
            <div class="dropdown">
                <button class="dropbtn">Search by</button>
                <div class="dropdown-content">
                    <a href="#" onclick="setSearchBy('firstName')">First Name</a>
                    <a href="#" onclick="setSearchBy('city')">City</a>
                    <a href="#" onclick="setSearchBy('email')">Email</a>
                    <a href="#" onclick="setSearchBy('phone')">Phone</a>
                </div>
            </div>
            <input type="text" id="searchInput" placeholder="Search..." onkeyup="searchCustomer()">
            <button onclick="syncCustomers()">Sync</button>
        </div>
    </header>
    <main>
        <table border="1">
            <thead>
                <tr>
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
                <%
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
                            <img src="images/edit.png" height="30px" width="30px" alt="Edit" title="Edit this customer" onclick="editCustomer('<%= customer.getId() %>')">
                            <img src="images/delete.png" height="30px" width="30px" alt="Delete" title="Delete this customer" onclick="deleteCustomer('<%= customer.getId() %>')">
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="pagination">
            <%
                int noOfPages = (Integer) request.getAttribute("noOfPages");
                int currentPage = (Integer) request.getAttribute("currentPage");
    
                if (currentPage > 1) {
                    out.print("<a class='pagination-btn' href='listCustomers?page=" + (currentPage - 1) + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>Previous</a> ");
                }
    
                for (int i = 1; i <= noOfPages; i++) {
                    if (i == currentPage) {
                        out.print("<span class='pagination-btn current'>" + i + "</span> ");
                    } else {
                        out.print("<a class='pagination-btn' href='listCustomers?page=" + i + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>" + i + "</a> ");
                    }
                }
    
                if (currentPage < noOfPages) {
                    out.print("<a class='pagination-btn' href='listCustomers?page=" + (currentPage + 1) + "&pageSize=" + request.getAttribute("pageSize") + "&sortField=" + request.getAttribute("sortField") + "&sortOrder=" + request.getAttribute("sortOrder") + "'>Next</a>");
                }
            %>
        </div>
    </main>
    <script>
    let searchBy = 'firstName';

    function setSearchBy(criteria) {
        searchBy = criteria;
    }

    function searchCustomer() {
        const input = document.getElementById('searchInput').value.toLowerCase();
        const table = document.getElementById('customerTableBody');
        const rows = table.getElementsByTagName('tr');

        let columnIndex;
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

    function syncCustomers() {
        window.location.href = 'syncCustomers';
    }

    function editCustomer(id) {
        window.location.href = 'updateCustomer?id=' + id;
    }

    function deleteCustomer(id) {
        if (confirm('Are you sure you want to delete this customer?')) {
            window.location.href = 'deleteCustomer?id=' + id;
        }
    }
    </script>
</body>
</html>
