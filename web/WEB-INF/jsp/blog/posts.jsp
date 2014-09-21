
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Last records</h1>
		<div>
			<a href="${properties['base.webpath']}/post/create">new post<a/>
		</div>
		<c:forEach items="${posts}" var="post">
			<div>
				${post.getName()}
			</div>
		</c:forEach>
    </body>
</html>
