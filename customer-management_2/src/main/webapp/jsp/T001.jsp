<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Login</h2>
    <html:form action="/userLogin" method="post">
        <table>
            <tr>
                <td>User ID:</td>
                <td><html:text property="userId" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><html:password property="password" /></td>
            </tr>
            <tr>
                <td colspan="2"><html:submit value="Login" /></td>
            </tr>
        </table>
        <logic:messagesPresent>
            <div class="error">
                <html:messages />
            </div>
        </logic:messagesPresent>
    </html:form>
</body>
</html>