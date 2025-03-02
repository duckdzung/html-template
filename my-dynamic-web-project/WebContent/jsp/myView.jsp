<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My View</title>
</head>
<body>
    <h1>Welcome to My View</h1>
    <p>This is a JSP page that displays data managed by the application.</p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="item" items="${myModelList}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <a href="edit?id=${item.id}">Edit</a>
                    <a href="delete?id=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="create">Create New Item</a>
</body>
</html>