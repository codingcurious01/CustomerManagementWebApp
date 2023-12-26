<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style type="text/css">
	<%@ include file="resources/css/styles.css" %>
</style>
<script type="text/javascript">
	<%@ include file="resources/js/functions.js" %>
</script>

</head>
<body>	

<!-- <nav class="navbar navbar-expand-lg gradient-background">
    <div class="container-mt1 d-flex align-items-center">
        <a class="navbar-brand fw-bold text-dark">BMart</a>
        <form action="adminLogin">
            <button class="btn btn-outline-light text-dark" type="submit">Admin</button>
        </form>
    </div>
</nav> -->

<nav class="navbar navbar-expand-lg gradient-background">
<!-- 	  <div class="container-fluid"> -->
	  	<div class="container mt-1">
	    <a class="navbar-brand fw-bold text-dark">BMart</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    
	      <!-- <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active text-light" aria-current="page" href="#">Home</a>
	        </li>
	      </ul> -->
	      <form class="d-flex ms-auto" action="adminLogin">
	        <!-- <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"> -->
	        <button class="btn btn-outline-light text-dark" type="submit">Admin</button>
	      </form>
	    </div>
	  </div>
	</nav>





	<!-- Container for centering the banner -->
	<div id="banner-container">
		<!-- Linking an image using a relative path -->
		<img id="banner" src="https://live.staticflickr.com/65535/53421527176_c99a8c87f5_z.jpg" alt="bmart-banner">
	</div>
	
	<!-- Footer view -->
	<%@ include file="resources/views/footer.jsp" %>
</body>
</html>
