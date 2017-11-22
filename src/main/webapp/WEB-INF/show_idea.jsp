<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/style.css" />
<title>Show Idea</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<a href="/bright_ideas">Bright Ideas</a>
	<div class="container">
		<p><a href="/users/${idea.user.id}"><c:out value="${idea.user.name}" /></a> says:
			<fieldset>
			<c:out value="${content}" />
			</fieldset>
		</p>
		
		<c:forEach var="user" items="${idea.users}">
			<p>${user.name}</p>
			<p>${user.alias}</p>
			<p>________________________</p>
		</c:forEach>
	</div>
</body>
</html>