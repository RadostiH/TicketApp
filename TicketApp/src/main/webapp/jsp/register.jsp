<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
crossorigin="anonymous">
<link rel="stylesheet" href="src/main/resources/login.css">
</head>
<body>
<div class = "container">
<div class = "row">
<div class="col-md-4"></div>

<div class="col-md-4">
  <form action="TicketApp/home" method="POST">
  	<h2>Register</h2>
  	<div class="container">
    	<div class = "row-3">
    	<label for="uname"><b>Username</b></label><br>
    	<input type="text" placeholder="Enter Username" name="uname" required>
    	</div>
    	<div class = "row-3">
    	<label for="psw"><b>Password</b></label><br>
        <input type="password" placeholder="Enter Password" name="psw" required>
    	</div>
    	<div class = "row-3">
    	<label for="psw"><b>Confirm Password</b></label><br>
        <input type="password" placeholder="Confirm Password" name="confPsw" required>
    	</div>
    	<div class = "row-3">
    	<label for="psw"><b>E-mail</b></label><br>
        <input type="email" placeholder="E-mail" name="psw" required>
    	</div>
    	<br>
    	<div class = "row-2">
    	<button type="submit" name = "Register" style = "margin:auto; width: 50%; height: 50%; ">Login</button>
    	</div>
    </div>
    </form>
  </div>
</div>
<div class="col-md-4"></div>
</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" 
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" 
crossorigin="anonymous"></script>
</html>