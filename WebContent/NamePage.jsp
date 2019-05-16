<%-- 1. The following tag allows the use of the JSP tags in this file: --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- 2. The following tag allows the use of the JSTL core tags in this file: --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Displaying a name with JSP/JSTL</title>
	<link rel="stylesheet" type="text/css" href="css/WebAppExamples.css">
</head>
<body>

	<p>
		<%-- Here we display the name using a JSTL tag --%>
		<c:out value="This is Vesa Huotarinen" />
	</p>

</body>
</html>