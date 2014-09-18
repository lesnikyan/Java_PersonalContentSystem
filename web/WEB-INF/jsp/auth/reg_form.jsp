<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="/perconsys/static/css/main.css" />
    </head>
    <body>
        <h1>User registration</h1>
        <sf:form method="POST" action="/perconsys/auth/register" modelAttribute="user">
            <div class="form-field">
				<sf:errors path="name" class="formError" /><br />
				<sf:input path="name" /> <sf:label path="name">Full Name</sf:label>
				
			</div>
            <div class="form-field">
				<sf:errors path="login" class="formError" /><br />
				<sf:input path="login" /> <sf:label path="login">Login</sf:label>
			</div>
            <div class="form-field">
				<sf:errors path="password" class="formError" /><br />
				<sf:input path="password" /> <sf:label path="password">Password</sf:label>
			</div>
            <div class="form-field">
				<sf:errors path="email" class="formError" /><br />
				<sf:input path="email" /> <sf:label path="email">Email</sf:label>
			</div>
            <div><input type="submit" name="Create" /></div>
        </sf:form>
    </body>
</html>