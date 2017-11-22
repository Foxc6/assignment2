<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/style.css" />
<title>Bright Ideas</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<h2>Welcome, ${userName}</h2>
	<div class="container">
		<form:form method="POST" action="/add_idea" modelAttribute="idea">
			<p>
				<form:label path="content">
					<form:input path="content" />
				</form:label>
			</p>

			<input type="submit" value="Add Idea">
		</form:form>


		<c:forEach var="idea" items="${ideas}">
			<p>
				<a href="/users/${idea.user.id}"><c:out
						value="${idea.user.name}" /></a> says:
			<fieldset>
				<c:out value="${idea.content}" />
			</fieldset>
			</p>

			<c:choose>
				<c:when test="${idea.user != user}">
					<form:form action="/addLike" method="POST" modelAttribute="userIdeas">
						<form:hidden path="idea" value="${idea.id}"></form:hidden>
						<input type="submit" value="Like" />
					</form:form>
				</c:when>
				<c:otherwise>
					<p><a href="/ideas/delete/${idea.id}">Delete</a></p>
				</c:otherwise>
			</c:choose>

			<p>
				<a href="/ideas/${idea.id}">${idea.users.size()} people</a> like
				this
			</p>
		</c:forEach>
	</div>
</body>
</html>