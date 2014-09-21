
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
    </head>
    <body>
        <h1>Edit blog info</h1>
		<sf:form method="POST" action="/perconsys/auth/register" modelAttribute="user">
			<sf:hidden path="id" /><%-- use blog's id for saving, but check auth by user session --%>
            <div class="form-field">
				<sf:errors path="name" class="formError" /><br />
				<sf:input path="name" /> <sf:label path="name">name</sf:label>
			</div>
            <div class="form-field">
				<sf:errors path="url_name" class="formError" /><br />
				<sf:input path="url_name" /> <sf:label path="url_name">User name (like "my_blog")</sf:label>
			</div>
			<div><input type="submit" name="Create" /></div>
        </sf:form>
    </body>
</html>
