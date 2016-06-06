<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <title>Employees Login</title>
 

</head>
	<body>
	<!--  The action = is the path ending the name of the servlet -->
	<%
		if (request.getSession(false) != null && request.getSession().getAttribute("employees_loggedin") != null)
		{
			response.sendRedirect("DBControl.jsp");
		}
	%>
		<div class="container">
			<div class="row" align="center" style="margin-top:100px;">
				<div class="col-lg-4 col-lg-offset-4 centered">
					
					<h3 style="font-family:Futura;">Employees Login</h3> 
				    <form name="loginform" action="_dashboard" method="POST">
					  <input name="email" placeholder="Email" required type="email"/>
					  <input name="password" type="password" placeholder="Password" required="required"/>
					  <input type="submit" value="Login" id="login_button"/>
				    </form>
				  <h4>${elogin_invalid}</h4>
		    
		</div>
		
		
		
		</div>
	
	</body>
</html>