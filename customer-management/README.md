# Customer Management Application

This project is a Customer Management application built using Struts 1, Servlet, JSP, JDBC, and MSSQL. It provides functionalities for user login, customer management, and data validation.

## Features

- **User Authentication**: 
  - Login and logout functionalities.
  
- **Customer Management**:
  - View a list of customers with pagination and search capabilities.
  - Add new customers and edit existing customer details.
  - Delete multiple customers using checkboxes.

## Project Structure

```
customer-management
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── example
│   │   │   │       ├── actions
│   │   │   │       ├── business
│   │   │   │       ├── dao
│   │   │   │       ├── forms
│   │   │   │       ├── models
│   │   │   │       └── utils
│   │   ├── resources
│   │   └── webapp
│   │       ├── WEB-INF
│   │       └── jsp
├── pom.xml
└── README.md
```

## Technologies Used

- **Java**: Programming language used for backend development.
- **Struts 1**: Framework for building web applications.
- **JSP**: JavaServer Pages for creating dynamic web content.
- **JDBC**: Java Database Connectivity for database operations.
- **MSSQL**: Microsoft SQL Server as the database management system.

## Setup Instructions

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Deploy the application on a servlet container (e.g., Apache Tomcat).
5. Configure the database connection in `src/main/resources/db.properties`.

## Usage

- Access the application through your web browser.
- Use the login page to authenticate users.
- Manage customer data through the provided interfaces.

## License

This project is licensed under the MIT License.