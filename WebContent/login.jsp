<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src='https://www.google.com/recaptcha/api.js'></script>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <title>FabFlix Login</title>
  <link href='http://fonts.googleapis.com/css?family=Futura:300,400,500' rel='stylesheet' type='text/css'>
  <link href="Style/boostrap.min.css" rel="stylesheet"/>

</head>
	<body>
	<!--  The action = is the path ending the name of the servlet -->
	<%
		if (request.getSession(false) != null && request.getSession().getAttribute("customer_loggedin") != null)
		{
			response.sendRedirect("FabFlixMain");
		}
	%>
		<div class="container">
			<div class="row" align="center" style="margin-top:100px;">
				<div class="col-lg-4 col-lg-offset-4 centered">
					<div class="fadein">
					  <img src="Images/film.jpeg"/>
					</div>
					<h3 style="font-family:Futura;">Login</h3> 
				    <form name="loginform" action="Login" method="POST">
					  <input name="email" placeholder="Email" required type="email"/>
					  <input name="password" type="password" placeholder="Password" required="required"/>
					  <input type="submit" value="Login" id="login_button"/>
					  <div class="g-recaptcha" data-sitekey="6Lc9Zx4TAAAAALvnVV1nCPcteXF2cXT-k0cY__Tc"></div>
				    </form>
				  <h4 style="color:red">${login_invalid}</h4>
		    
		
		
				<div id="main_content">
				  <h2 style="font-family:Futura;">
				  Welcome to FabFlix! Your one stop shop for online movie shopping!
				  </h2>
				</div>
			  </div>
		</div>
		
		
		
		</div>
	
	</body>
</html>