
<%-- The following two tags allow the use of the JSP and JSTL tags in this source text --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Student List</title>
<link rel="stylesheet" type="text/css" href="css/WebAppExamples.css">
</head>
<body>
	<h1>Java Web Programming Examples</h1>
	<h2>JSP/JSTL + Servlet + DAO</h2>

	<div>
		<h3>List of Students</h3>

		<br /> <br />

		<%-- If this JSP page is requested with a parameter called 'studentList' 
		     and the list is not empty, then show the data in an HTML table.
		 --%>

		<table>
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Last Name</th>
					<th>First Name</th>
					<th>Street</th>
					<th>Postcode</th>
					<th>Post Office</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${ studentList }" var="studentObject">
					<tr>
						<td><c:out value="${ studentObject.id }" /></td>
						<td><c:out value="${ studentObject.firstName }" /></td>
						<td><c:out value="${ studentObject.lastName }" /></td>
						<td><c:out value="${ studentObject.streetAddress }" /></td>
						<td><c:out value="${ studentObject.postCode }" /></td>
						<td><c:out value="${ studentObject.postOffice }" /></td>
						<td><a
							href='DeleteStudent?studentId=<c:out value="${ studentObject.id }" />'
							onClick="return confirm('Do you want to delete the student?')">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<%-- If the list is empty, then show an appropriate message --%>
		<c:if test="${ studentList != null && studentList.size() == 0 }">
			No students found
		</c:if>
	</div>

</body>
</html>