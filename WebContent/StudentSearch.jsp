
<%-- The following two tags allow the use of the JSP and JSTL tags in this source text --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Search</title>
<link rel="stylesheet" type="text/css" href="css/WebAppExamples.css">
</head>
<body>
	<h1>Java Web Programming Examples</h1>
	<h2>JSP/JSTL + Servlet + DAO</h2>

	<div>
		<h3>Search Student</h3>

		<br />

		<form action="studentSearchService" method="GET">
			<label>Student ID: </label> <input type="text" name="txtId" /> <input
				type="submit" value="Search" />
		</form>

		<br />

		<c:if test="${ student.id == -1 }">
			No student found for the given id: <c:out value="${ txtId }" />
		</c:if>

		<c:if test="${ student.id > 0 }">
			<c:out value="${ student.id } " />
			<c:out value="${ student.firstName } " />
			<c:out value="${ student.lastName } " />
		</c:if>

	</div>

</body>
</html>