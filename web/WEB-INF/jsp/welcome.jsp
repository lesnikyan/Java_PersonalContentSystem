
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="/perconsys/static/css/main.css" />
    </head>
    <body>
        <h1>PersonilizedContent System</h1>
		<c:choose >
			<c:when test="${loggedin}">
				<div>
					Hello ${user.getName()}!
				</div>
				<div>
					<a href="${properties['base.webpath']}/editblog">My blogs</a>
				</div>
			</c:when>
			<c:otherwise>
				<a href="${properties['base.webpath']}/auth/register">Register and create personal blog</a><br>
				<a href="${properties['base.webpath']}/auth/login">Login</a><br>
			</c:otherwise>
		</c:choose>
		
    </body>
</html>
