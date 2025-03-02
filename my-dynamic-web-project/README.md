# My Dynamic Web Project

This project is a dynamic web application built using Struts 1, Servlet, JSP, Spring 2.5.6, and Hibernate 3.2.7. It implements a CRUD (Create, Read, Update, Delete) functionality and follows the MVC (Model-View-Controller) design pattern.

## Project Structure

```
my-dynamic-web-project
├── src
│   ├── com
│   │   └── example
│   │       ├── controller
│   │       │   └── MyController.java
│   │       ├── model
│   │       │   └── MyModel.java
│   │       ├── service
│   │       │   └── MyService.java
│   │       └── dao
│   │           └── MyDao.java
├── WebContent
│   ├── WEB-INF
│   │   ├── web.xml
│   │   ├── struts-config.xml
│   │   ├── applicationContext.xml
│   │   └── lib
│   ├── jsp
│   │   └── myView.jsp
│   └── index.jsp
├── build.xml
├── pom.xml
└── README.md
```

## Technologies Used

- **Struts 1**: Used as the MVC framework to handle requests and responses.
- **Servlets and JSP**: Used for creating dynamic web pages and handling user interactions.
- **Spring 2.5.6**: Used for managing beans and providing dependency injection.
- **Hibernate 3.2.7**: Used for database operations and ORM (Object-Relational Mapping).

## Setup Instructions

1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   ```

2. **Build the Project**: 
   Use Ant or Maven to build the project.
   ```
   ant build
   ```
   or
   ```
   mvn clean install
   ```

3. **Deploy the Application**: 
   Deploy the generated WAR file to a servlet container (e.g., Apache Tomcat).

4. **Access the Application**: 
   Open a web browser and navigate to `http://localhost:8080/my-dynamic-web-project`.

## Usage Guidelines

- The main entry point of the application is `index.jsp`.
- Use the provided JSP views to interact with the application.
- The controller (`MyController.java`) handles all incoming requests and directs them to the appropriate service methods for processing.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.