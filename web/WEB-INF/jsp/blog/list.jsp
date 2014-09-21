
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.perconsys.entities.User"%>
<%@page import="org.perconsys.entities.Blog"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of my blogs</h1>
		<div>
		<c:forEach items="${blogs}" var="blog">
			<div>
				${blog.getName()}
				- <a href="${properties['base.webpath']}/blog/edit/${blog.getId()}">edit<a/>
				- <a href="${properties['base.webpath']}/blog/view/${blog.getId()}">view<a/>
			</div>
		</c:forEach>
		</div>
		<div>
			<a href="${properties['base.webpath']}/blog/create">Create blog</a>
		</div>
    </body>
</html>
