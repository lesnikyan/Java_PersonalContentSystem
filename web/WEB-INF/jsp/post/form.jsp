
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
			<sf:form method="POST" action="${properties['base.webpath']}/editpost/save" modelAttribute="post">
				<sf:hidden path="id" />			
				<input type="hidden" value="${blogId}" name="blog_id" />			
            <div class="form-field post-field">
				<sf:input path="title" /><br />
				<sf:textarea path="content" />
			</div>
			<div><input type="submit" name="Save" /></div>
			</sf:form>
		</div>
    </body>
</html>
