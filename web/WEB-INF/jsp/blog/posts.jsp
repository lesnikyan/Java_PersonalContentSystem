
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
        <h1>Last records</h1>
		<div class="action-buttons">
			<c:if test="${authorized}">
				<a href="${properties['base.webpath']}/editpost/create?blog=${blog.getId()}" class="butt">New post<a/>
			</c:if>
			<c:if test="${!authorized}">
				<a href="${properties['base.webpath']}/auth/login">Login<a/>
			</c:if>
			
		</div>
		<div class="posts-list">
		<c:forEach items="${posts}" var="post">
			<div class="post-container">
				<div class="post-title"><a href="${properties['base.webpath']}/post/${post.getId()}">${post.getTitle()}</a></div> 
				<div class="post-content">${post.getContent()}</div> 
			</div>
		</c:forEach>
		</div>
    </body>
</html>
