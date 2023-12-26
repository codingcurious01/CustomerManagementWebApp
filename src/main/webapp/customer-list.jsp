<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Customer List</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style type="text/css">
	<%@ include file="resources/css/styles.css" %>	
</style>
</head>
<body>
<%
	// Browsers can understand [Cache-Control, Progma, Expires]
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Expires","0");
		response.setDateHeader("Expires",-1);
/* 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Progma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies [0 means minutes]
*/
		
	if(session.getAttribute("username") == null){
	    response.sendRedirect("admin-login.jsp");
	}
%>
	<%@ include file="resources/views/navbar.jsp" %>
	<br>
	<div class="container">
	<h1 align="right" class="fw-bold">Welcome! ${username}</h1>
	<h2 align="center" class="fw-bold">Customers List</h2>
	<hr>

	<form action="insertForm">
		<button class="btn btn-outline-dark btn-lg btnSubmit">Add Customer</button>
	</form>
	<br>
	
	<table class="table">
	  <thead>
	    <tr>
	      	<th scope="col">Id</th>
			<th scope="col">Name</th>
			<th scope="col">Mobile</th>
			<th scope="col">Purchased State</th>
			<th scope="col">Email</th>
			<th scope="col">Gender</th>
			<th scope="col">Actions</th>
	    </tr>
	  </thead>
	  
	  <tbody>
		
		<c:forEach var="i" items="${alCustomers}" >
			<tr>
				<td><c:out value="${i.id}"></c:out></td>
				<td><c:out value="${i.name}"></c:out></td>
				<td><c:out value="${i.mobile}"></c:out></td>
				<td><c:out value="${i.state}"></c:out></td>
				<td><c:out value="${i.email}"></c:out></td>
				<td><c:out value="${i.gender}"></c:out></td>
				
									<!-- Query String  -->
				<td> 
					<a class="btn btn-outline-dark btnSubmit" href="editForm?id=<c:out value="${i.id}"></c:out>">Edit</a> 
					&nbsp;&nbsp; 
					<a class="btn btn-outline-dark btnSubmit" href="delete?id=<c:out value="${i.id}"></c:out>">Delete</a> 
				</td>
			</tr>
		</c:forEach>
	  
	  		
	  </tbody>
	  
	  </table>
</div>

<br><br>
<%@ include file="resources/views/footer.jsp" %>

 

<script type="text/javascript">
	<%@ include file="resources/js/functions.js" %>
</script>
</body>
</html>