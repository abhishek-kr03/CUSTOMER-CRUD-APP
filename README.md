# CustomerCRUDApp

## Overview
CustomerCRUDApp is a web application designed for managing customer data. It features functionalities for creating, reading, updating, and deleting customer information, as well as synchronizing with an external Sunbase database. The application employs stateless authentication using JSON Web Tokens (JWT) and is built using Java Servlets, JSP, and a MySQL database.

## Features
- **Add, Update, Delete Customers**: Full CRUD operations for managing customer data.
- **Search and Filter**: Search customers by first name, city, email, or phone.
- **Pagination**: Navigate through customer records with paginated views.
- **Sync Customers**: Synchronize customer data with the Sunbase database.
- **Stateless Authentication**: Secure login with JWT.
- **Responsive Design**: User-friendly interface with responsive design.

## Project Structure
```
CustomerCRUDApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── customer/
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   ├── CustomerDao.java
│   │   │   │   │   │   ├── CustomerDaoImpl.java
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Customer.java
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   ├── AddCustomerServlet.java
│   │   │   │   │   │   ├── AuthServlet.java
│   │   │   │   │   │   ├── DeleteCustomerServlet.java
│   │   │   │   │   │   ├── ListCustomersServlet.java
│   │   │   │   │   │   ├── SelectCustomerServlet.java
│   │   │   │   │   │   ├── UpdateCustomerServlet.java
│   │   │   │   │   │   ├── SyncServlet.java
│   │   │   │   │   ├── util/
│   │   │   │   │   │   ├── AuthAPIUtil.java
│   │   │   │   │   │   ├── CustomerAPIUtil.java
│   │   │   │   │   │   ├── JWTUtil.java
│   │   ├── resources/
│   │   ├── webapp/
│   │   │   ├── images/
│   │   │   │   ├── edit.png
│   │   │   │   ├── delete.png
│   │   │   ├── addCustomer.jsp
│   │   │   ├── index.jsp
│   │   │   ├── listCustomers.jsp
│   │   │   ├── login.jsp
│   │   │   ├── updateCustomer.jsp
│   │   │   ├── styles.css
├── pom.xml
└── README.md

```

## Technologies Used
- **Frontend**: HTML, CSS, JavaScript, JSP
- **Backend**: Java Servlets, JDBC, JWT (using JSON.simple, jjwt, jjwt-api libraries)
- **Database**: MySQL
- **Tools**: Git, GitHub

## Setup and Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/CustomerCRUDApp.git
    cd CustomerCRUDApp
    ```

2. **Setup the MySQL database:**
    - Create a database named `customerdb`.
    - Run the SQL script to create the `customers` table.

3. **Configure the database connection:**
    - Update the `db.properties` file with your MySQL database credentials.

4. **Build the project:**
    ```sh
    mvn clean install
    ```

5. **Deploy the application:**
    - Deploy the `war` file to a servlet container like Apache Tomcat.

6. **Access the application:**
    - Open your web browser and navigate to `http://localhost:8080/CustomerCRUDApp`.

## Usage
- **Login**: Access the login page and authenticate using your credentials.
- **Dashboard**: View the list of customers with pagination.
- **Add Customer**: Navigate to the add customer page to create a new customer.
- **Update Customer**: Edit customer details by navigating to the update customer page.
- **Delete Customer**: Remove a customer by clicking the delete icon.
- **Search Customer**: Use the search functionality to filter customers.
- **Sync Customers**: Sync customer data with the Sunbase database.

## Libraries and Dependencies
- **JSON.simple**: For JSON parsing and handling.
- **jjwt, jjwt-api**: For handling JSON Web Tokens (JWT).
- **JSTL**: JavaServer Pages Standard Tag Library for JSP.

## Contributing
Feel free to fork this repository and contribute by submitting pull requests. Any improvements and suggestions are welcome!

## License
This project is licensed under the MIT License.

## Acknowledgements
- **OpenAI's ChatGPT**: For providing assistance in resolving issues and improving the project.
- **Stack Overflow**: For providing valuable resources and solutions to various coding challenges.
- **Maven Repository**: For offering access to a wide range of libraries and dependencies crucial for the project.
---

Feel free to modify the README content according to your specific details and preferences.

---

![Screenshot 2024-07-23 104448](https://github.com/user-attachments/assets/f51f8766-4059-4be8-a89f-6e32d055b031)

![Screenshot 2024-07-23 104542](https://github.com/user-attachments/assets/ae53deea-7b15-4bd1-b95d-ad37ea58648a)

![Screenshot 2024-07-23 104616](https://github.com/user-attachments/assets/e000451b-f6c1-4855-8169-451ea36306b6)

![Screenshot 2024-07-23 104646](https://github.com/user-attachments/assets/9a9f5adb-f5e8-46be-8c6d-97b3179cba3c)

![Screenshot 2024-07-23 104709](https://github.com/user-attachments/assets/80145422-b539-49af-aa23-54cb281ec232)

![Screenshot 2024-07-23 104732](https://github.com/user-attachments/assets/ec176003-4ee5-44ae-a2db-9187074bc4db)
