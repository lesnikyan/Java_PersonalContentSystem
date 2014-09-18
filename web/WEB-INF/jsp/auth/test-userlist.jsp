
<%@page import="java.util.List"%>
<%@page import="org.perconsys.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Users</h1>
		<table>
		<% for(User user :  (List<User>) request.getAttribute("users")){ %>
		<tr>
			<td><%=user.getName()%></td>
			<td><%=user.getEmail()%></td>
		</tr>
		<%}%>
		</table>
    </body>
</html>
