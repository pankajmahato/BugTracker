<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page isELIgnored="false"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Bug</title>
</head>
<body>
	Welcome user,
	<br>
	<a href="./login">Click here to go back to login</a>
	<form action="submitBug.do" method="post">
		<table>
			<tr>
				<td>Enter Title</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>Enter Description</td>
				<td><textarea cols="60" rows="5" name="description"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="${userId }" name="userId"></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<c:if test="${not empty userBugs && not empty userBugs.bugListDto }">
		<table border="1">
			<tr>
				<td>Bug ID</td>
				<td>Title</td>
				<td>Description</td>
				<td>Submitted Date</td>
				<td>Submitted By</td>
				<td>Assigned To</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${userBugs.bugListDto}" var="bug">
				<tr>
					<td>${bug.id }</td>
					<td>${bug.title }</td>
					<td>${bug.description }</td>
					<td>${bug.dateSubmitted }</td>
					<td>${bug.user.name }</td>
					<td>${bug.support.name }</td>
					<td>${bug.status }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<form action="logout">
		<input type="submit" value="Logout"><input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>