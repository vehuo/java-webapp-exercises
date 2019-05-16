
<%-- The following two tags allow the use of the JSP and JSTL tags in this source text --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Insert</title>
<link rel="stylesheet" type="text/css" href="css/WebAppExamples.css">
</head>
<body>
	<h1>Java Web Programming Examples</h1>
	<h2>JSP/JSTL + Servlet + DAO</h2>

	<div>
		<h3>Insert Student</h3>

		<br />

		<form action="InsertStudent" method="GET">
			<label>Student ID: </label> <input type="text" name="txtId" />
			<label>First Name: </label> <input type="text" name="txtFirstName" />
			<label>Last Name: </label> <input type="text" name="txtLastName" />
			<label>Street Address: </label> <input type="text" name="txtStreetAddress" />
			<label>Post code: </label> <input type="text" name="txtPostCode" />
			<label>Post Office: </label> <input type="text" name="txtPostOffice" />
			<input type="submit" value="Search" />
		</form>

		<br />

		<c:out value="${ message } " />

	</div>

</body>
</html>