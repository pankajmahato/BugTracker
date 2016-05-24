<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>${message }
	<form action="<c:url value='j_spring_security_check' />" method="post">
		<table>
			<tr>
				<td>Enter username</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Enter password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login"></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>