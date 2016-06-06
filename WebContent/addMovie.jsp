<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Movie</title>
</head>
<body>
<%
		if (request.getSession().getAttribute("employees_loggedin")==null||!(Boolean)request.getSession().getAttribute("employees_loggedin") )
		{
			response.sendRedirect("employeeslogin.jsp");
		}
	%>
	<div class="container" style="margin-top:30px;margin-right:50px;">
<div class="row">
  <div class="span7 offset5">
	<div style="position: absolute;top: 100px;left:350px;font-family: Arial;">
		<div>Add Movie</div><br>
			<div style="font-size:20px; margin-left: auto;margin-right: auto; width: 20em; ">
				<form name="AddStar" action="AddMovie" method="GET">
					<span id="form1">Movie Title<br></span>
				<input type="text" name="title" size="50" id="cc_id" maxlength="20" value="" required="required"/><br>
					<span id="form2">Movie Year<br></span>
				<input type="text" name="year" size="50" id="cc_id" maxlength="20" value="" required="required"/><br>
				<span id="form2">Movie Director<br></span>
				<input type="text" name="director" size="50" id="cc_id" maxlength="20" value="" required="required"><br>
				<span id="form2">Star First Name<br></span>
				<input type="text" name="first_name" size="50" id="cc_id" maxlength="20" value="" required="required"/><br>
					<span id="form2">Star Last Name<br></span>
				<input type="text" name="last_name" size="50" id="cc_id" maxlength="20" value="" required="required"/><br>
				<span id="form2">Genre<br></span>
				<input type="text" name="genre" size="50" id="cc_id" maxlength="20" value="" required="required"/><br><br>
				<input type="submit" name="Add Movie" value="Submit"/>
				</form>
				<br>
				<form action="DBControl.jsp">
    				<input type="submit" value="Back to Menu">
				</form>
				<h3>${movie_result}</h3>
			</div>
	</div>
</div>
</div>
</div>
</body>
</html>