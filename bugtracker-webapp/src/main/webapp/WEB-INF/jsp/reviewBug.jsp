<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" action="reviewBug.do"
		modelAttribute="bugListDto">
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
			<c:forEach items="${bugListDto.bugListDto}" var="bug"
				varStatus="status">
				<tr>
					<td>${bug.id }</td>
					<form:input path="bugListDto[${status.index}].id" type="hidden" />
					<td>${bug.title }</td>
					<td>${bug.description }</td>
					<td>${bug.dateSubmitted }</td>
					<td>${bug.user.name }</td>
					<td>${bug.support.name }
					<td><form:checkbox path="bugListDto[${status.index}].status"
							value="CLOSED" label="RESOLVED" /></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Submit">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>
</body>
</html>