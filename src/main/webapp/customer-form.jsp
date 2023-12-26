<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
<title>Customer Form</title>
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style type="text/css">
	<%@ include file="resources/css/styles.css" %>
</style>
</head>
<body>
<%
		//Browsers can understand [Cache-Control, Progma, Expires]
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Expires","0");
		response.setDateHeader("Expires",-1);
	/*
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Progma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies [0 means minutes] 
	response.setHeader("Expires", -1); // Proxies 
		*/
		
	if(session.getAttribute("username") == null){
	    response.sendRedirect("admin-login.jsp");
	}


%>
	<%@ include file="resources/views/navbar.jsp" %>
	<br>
	<div class="customer-form-center-container">
		<div class="customer-centered-form-container">
			<c:if test="${customer != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${customer == null}">
				<form action="insert" method="post">
			</c:if>

			<caption>
				<h2>
					<c:if test="${customer != null}">
                                    Edit Customer
                                </c:if>
					<c:if test="${customer == null}">
                                    Add New Customer
                                </c:if>
				</h2>
			</caption>

			<c:if test="${customer != null}">
				<input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
			</c:if>

			<label for="name">Name:<span class="mandatoryField">*</span></label> 
			<input type="text" id="name" class="col-12" value="<c:out value='${customer.name}' />" name="name" required="required" /> 
			
			<label for="mobile">Mobile:<span class="mandatoryField">*</span></label> 
			<input type="tel" id="mobile" class="col-12" value="<c:out value='${customer.mobile}' />" name="mobile" required="required" /> 
			
			<label for="state">State:<span class="mandatoryField">*</span></label>
			<select id="state" name="state" class="col-12" required="required">
				<option value="Null"<c:if test="${customer != null && customer.state == 'Null'}"> selected </c:if>>--select--</option>
			    <option value="Karnataka" <c:if test="${customer != null && customer.state == 'Karnataka'}"> selected </c:if>> KA </option>
			    <option value="Tamil Nadu" <c:if test="${customer != null && customer.state == 'Tamil Nadu'}">selected</c:if>> TN </option>
			    <option value="Andhra Pradesh" <c:if test="${customer != null && customer.state == 'Andhra Pradesh'}">selected</c:if>> AP </option>
			    <option value="Telangana" <c:if test="${customer != null && customer.state == 'Telangana'}">selected</c:if>> TS </option>
			    <option value="Kerala" <c:if test="${customer != null && customer.state == 'Kerala'}">selected</c:if>> KL </option>
			</select>
			
			<label for="email">Email:</label> 
			<input type="email" id="email" class="col-12" value="<c:out value='${customer.email}' />" name="email" required="required" />
			
			<label>Gender:</label>
		    <div class="customer-form-radio-buttons">
		            <input type="radio" id="male" name="gender" value="Male" <c:if test="${customer != null && customer.gender == 'Male'}">checked</c:if> />
		            <label for="male">Male</label>
		
		            <input type="radio" id="female"  name="gender" value="Female" <c:if test="${customer != null && customer.gender == 'Female'}">checked</c:if> />
		            <label for="female">Female</label>
		
		            <input type="radio" id="other" name="gender" value="Other" <c:if test="${customer != null && customer.gender == 'Other'}">checked</c:if> />
		            <label for="other">Other</label>
		    </div>
		    <br>
		    <div class="text-center">
				<button class="btn col-12 btn-outline-dark btnSubmit">Save</button>
			</div>
			</form>
		</div>
	</div>
	
	<br><br>
<%@ include file="resources/views/footer.jsp" %>

 

<script type="text/javascript">
	<%@ include file="resources/js/functions.js" %>
</script>

</body>
</html>