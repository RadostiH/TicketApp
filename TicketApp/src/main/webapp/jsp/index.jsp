<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>

			<div class="col-md-4">

				<form action="/TicketApp/login" method="POST">
					<h2>Ticket Application</h2>
					<div class="container">
					<%if(request.getAttribute("error") != null){ %>
					<%=request.getAttribute("error") %>
					<%} %>
						<div class="row-5">
							<label for="uname"><b>Username</b></label><br> <input
								type="text" placeholder="Enter Username" name="uname" required>
						</div>
						<div class="row-5">
							<label for="psw"><b>Password</b></label><br> <input
								type="password" placeholder="Enter Password" name="psw" required>
						</div>
						<br>
						<div class="row-2">
							<button type="submit" value="Login" name="login"
								style="margin: auto; width: 50%; height: 50%;">Login</button>
						</div>
					</div>
				</form>

				<div class="container">
					<div class="row-2">
						<form action="TicketApp/register" method="POST">
							<input type="submit" value="Register" name="register"
								style="margin: auto; width: 50%; height: 50%;">
						</form>
					</div>
				</div>

			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
