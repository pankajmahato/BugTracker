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
	Welcome, Admin
	<form:form method="POST" action="assignBug.do"
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
			<c:forEach items="${adminBugs.bugListDto}" var="bug"
				varStatus="status">
				<tr>
					<td>${bug.id }<input name="bugListDto[${status.index}].id"
						type="hidden" value="${bug.id }" /></td>
					<td>${bug.title }</td>
					<td>${bug.description }</td>
					<td>${bug.dateSubmitted }</td>
					<td>${bug.user.name }</td>
					<td><c:choose>
							<c:when test="${empty bug.support}">
								<form:select path="bugListDto[${status.index}].support.id">
									<form:option value="0">Select</form:option>
									<form:options items="${employeeList}" itemLabel="name"
										itemValue="id" />
								</form:select>
							</c:when>
							<c:otherwise> ${bug.support.name }
				    		</c:otherwise>
						</c:choose></td>
					<%-- <form:input path="bugListDto[${status.index}].support"
						type="hidden" /> --%>
					<td>${bug.status }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>