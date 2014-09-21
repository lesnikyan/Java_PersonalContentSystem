
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="/perconsys/static/css/main.css" />
    </head>
    <body>
        <h1>Authorization</h1>
		<c:if test="${incorrect == true}">
			<div class="formError">Incorrect login or password!</div>
		</c:if>
		
		<sf:form method="POST" action="${properties['base.webpath']}/auth/login"  modelAttribute="user">
			 <div class="form-field">
				<sf:input path="login" /> <sf:label path="login">Login</sf:label>
			</div>
            <div class="form-field">
				<sf:input path="password" /> <sf:label path="password">Password</sf:label>
			</div>
            <div><input type="submit" name="Log in" /></div>
		</sf:form>
    </body>
</html>
