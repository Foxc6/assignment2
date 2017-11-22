<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/style.css" />
<title>Show User</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<a href="/bright_ideas">Bright Ideas</a>
	<div class="container">
		<p>Name: ${user.name}</p>
		<p>Alias: ${user.alias}</p>
		<p>Email: ${user.email}</p>
		<p>___________________________________________________________</p>
		<p>Total Number of Posts: ${count}</p>
		<p>Total Number of Likes: ${likeCount}</p>
	</div>
</body>
</html>