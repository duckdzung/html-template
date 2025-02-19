<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <title>Customer Management - Customer List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Customer List</h1>

<logic:present name="customerList">
    <form action="CustomerAction.do" method="post">
        <input type="text" name="searchName" placeholder="Search by Name" />
        <input type="submit" value="Search" />
    </form>

    <table border="1">
        <tr>
            <th>Select</th>
            <th>ID</th>
            <th>Name</th>
            <th>Sex</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        <logic:iterate id="customer" name="customerList">
            <tr>
                <td><input type="checkbox" name="selectedCustomers" value="<bean:write name='customer' property='id'/>" /></td>
                <td><bean:write name="customer" property="id"/></td>
                <td><bean:write name="customer" property="name"/></td>
                <td><bean:write name="customer" property="sex"/></td>
                <td><bean:write name="customer" property="birthday"/></td>
                <td><bean:write name="customer" property="email"/></td>
                <td><bean:write name="customer" property="address"/></td>
                <td>
                    <a href="CustomerAction.do?action=edit&id=<bean:write name='customer' property='id'/>">Edit</a>
                    <a href="CustomerAction.do?action=delete&id=<bean:write name='customer' property='id'/>">Delete</a>
                </td>
            </tr>
        </logic:iterate>
    </table>

    <input type="submit" value="Delete Selected" onclick="return confirm('Are you sure you want to delete selected customers?');" />
    
    <div>
        <logic:present name="pagination">
            <a href="<bean:write name='pagination' property='prevPage'/>">Previous</a>
            <span>Page <bean:write name='pagination' property='currentPage'/> of <bean:write name='pagination' property='totalPages'/></span>
            <a href="<bean:write name='pagination' property='nextPage'/>">Next</a>
        </logic:present>
    </div>
</logic:present>

<logic:notPresent name="customerList">
    <p>No customers found.</p>
</logic:notPresent>

</body>
</html>