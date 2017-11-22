<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/style.css" />
<title>Login Registration</title>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>Registration</legend>
			<form:errors path="user.*"/>
			
			<form:form method="POST" action="/register/user" modelAttribute="user">
				<p>
				<form:label path="name">Name:
				<form:input path="name"/>
				</form:label>
				</p>
				
				<p>
				<form:label path="alias">Alias:
				<form:input path="alias"/>
				</form:label>
				</p>
				
				<p>
				<form:label path="email">Email:
				<form:input type="email" path="email"/>
				</form:label>
				</p>
				
				<p>
				<form:label path="password">Password:
				<form:input type="password" path="password"/>
				</form:label>
				</p>
				
				<p>
				<label for="c_password">Confirm Password: </label>
				<input type="password" name="c_password" id="c_password">
				</p>
				
				<input type="submit" value="Register">
				
			</form:form>
		</fieldset>

		<fieldset>
			<legend>Login</legend>
			<form:errors path="user.*"/>
			<form action="/login/user" method="POST">
			<p>Email: <input type="text" name="email"></p>
			<p>Password: <input type="password" name="password"></p>
			<input type="submit" value="Login">
			</form>
		</fieldset>
	</div>
</body>
</html>