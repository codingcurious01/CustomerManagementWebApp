<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin Login</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- Bootstrap Icons CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" rel="stylesheet">
<style type="text/css">
	<%@ include file="resources/css/styles.css" %>
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg gradient-background">
	  <!-- 	  <div class="container-fluid"> -->
	  	<div class="container mt-1">
	    <a class="navbar-brand fw-bold text-dark">BMart</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	  </div>
	</nav>
	
	<div class="admin-center-container">	
		<div class="admin-centered-form-container">
		<h2>Admin Login</h2>
		  <!-- Your form controls go here -->
		  <form action="adminValidate" method="post">
		    <label for="username">Username:<span class="mandatoryField">*</span></label>
		    <input type="text" id="username" name="username" required="required">
			
			<div class="admin-password-container">
			    <label for="password">Password:<span class="mandatoryField">*</span></label>
			    <input type="password" id="password" name="password" required="required">
			    <i id="togglePassword" class="bi bi-eye admin-password-toggle"></i>
		    </div>
			
			<div class="text-center">
		    	<button class="btnSubmit" type="submit">Submit</button>
		    </div>
		  </form>
		</div>
	</div>
	
<%@ include file="resources/views/footer.jsp" %>
	
<script type="text/javascript">
	<%@ include file="resources/js/functions.js" %>
	document.addEventListener('DOMContentLoaded', function () {
	    var passwordField = document.getElementById('password');
	    var toggleButton = document.getElementById('togglePassword');

	    toggleButton.addEventListener('click', function () {
	        if (passwordField.type === 'password') {
	            passwordField.type = 'text';
	            toggleButton.classList.remove('bi-eye');
	            toggleButton.classList.add('bi-eye-slash');
	        } else {
	            passwordField.type = 'password';
	            toggleButton.classList.remove('bi-eye-slash');
	            toggleButton.classList.add('bi-eye');
	        }
	    });
	});
</script>
	
</body>
</html>