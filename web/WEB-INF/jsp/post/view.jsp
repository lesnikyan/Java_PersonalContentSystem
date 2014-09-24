<%-- 
    Document   : post
    Created on : 16.09.2014, 12:16:49
    Author     : Less
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="/perconsys/static/css/main.css" />
    </head>
    <body>
        <h1>${post.getTitle()}</h1>
		<div class="post-blog-links"><a href="${properties['base.webpath']}/blog/${blog.getId()}"> blog ${blog.getName()}</a></div>
		<div class="post-date">
			${post.getDate()}
		</div>
		<div class="port-content">
			${post.getContent()}
		</div>
		<div>
			Comments
			<span class="comments-count">(${comments.size()})</span>
		</div>
    </body>
</html>
