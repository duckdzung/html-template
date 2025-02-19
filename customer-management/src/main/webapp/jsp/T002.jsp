<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <title>Customer Management</title>
</head>
<body>
<h1>Customer List</h1>

<html:form action="/customerList">
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
        <logic:iterate property="customerList" name="customerForm">
            <tr>
                <td><html:checkbox property="selectedCustomers" value="<bean:write name='customerForm' property='id'/>"/></td>
                <td><bean:write name="customerForm" property="id"/></td>
                <td><bean:write name="customerForm" property="name"/></td>
                <td><bean:write name="customerForm" property="sex"/></td>
                <td><bean:write name="customerForm" property="birthday"/></td>
                <td><bean:write name="customerForm" property="email"/></td>
                <td><bean:write name="customerForm" property="address"/></td>
                <td>
                    <html:link action="/editCustomer?id=<bean:write name='customerForm' property='id'/>">Edit</html:link>
                </td>
            </tr>
        </logic:iterate>
    </table>
    
    <html:submit value="Delete Selected"/>
</html:form>

<h2>Search Customers</h2>
<html:form action="/searchCustomers">
    Name: <html:text property="name"/>
    Sex: <html:select property="sex">
        <html:options collection="sexOptions" property="value" label="label"/>
    </html:select>
    Birthday From: <html:text property="birthdayFrom"/>
    Birthday To: <html:text property="birthdayTo"/>
    <html:submit value="Search"/>
</html:form>

<p>
    <html:link action="/addCustomer">Add New Customer</html:link>
</p>

</body>
</html>