
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
		<div>
			<sf:form method="POST" action="${properties['base.webpath']}/post/edit" modelAttribute="post">
				
			</sf:form>
		</div>
    </body>
</html>
