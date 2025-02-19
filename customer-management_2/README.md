# README.md

# Customer Management Application

This project is a Customer Management Application built using Struts 1, Servlet, JSP, JDBC, and MSSQL. It provides functionalities for managing customer data, including user authentication, customer addition, updating, and deletion.

## Project Structure

```
customer-management
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── actions
│   │   │   ├── business
│   │   │   ├── dao
│   │   │   ├── forms
│   │   │   ├── models
│   │   │   └── utils
│   │   ├── resources
│   │   └── webapp
│   │       ├── WEB-INF
│   │       └── jsp
├── pom.xml
└── README.md
```

## Features

- User login and logout
- Customer management (add, update, delete)
- Search functionality with pagination
- Input validation for user and customer forms

## Technologies Used

- Struts 1
- Servlet
- JSP
- JDBC
- MSSQL

## Getting Started

1. Clone the repository.
2. Configure the database connection in the `src/main/resources/struts-config.xml`.
3. Build the project using Maven.
4. Deploy the application on a servlet container (e.g., Apache Tomcat).

## License

This project is licensed under the MIT License.