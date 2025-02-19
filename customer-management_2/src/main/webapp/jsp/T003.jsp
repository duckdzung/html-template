<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <title>Customer Management - Add/Edit Customer</title>
</head>
<body>
<h2>Add/Edit Customer</h2>
<html:form action="customerAction" method="post">
    <table>
        <tr>
            <td>ID:</td>
            <td><html:text property="id" readonly="true"/></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><html:text property="name" required="true"/></td>
        </tr>
        <tr>
            <td>Sex:</td>
            <td>
                <html:select property="sex">
                    <html:option value="M">Male</html:option>
                    <html:option value="F">Female</html:option>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><html:text property="birthday" required="true"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><html:text property="email" required="true"/></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><html:text property="address" required="true"/></td>
        </tr>
    </table>
    <html:submit value="Save"/>
    <html:button value="Cancel" onclick="window.location='T002.jsp';"/>
</html:form>
</body>
</html>