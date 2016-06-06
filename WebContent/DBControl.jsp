<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if (request.getSession().getAttribute("employees_loggedin")==null||!(Boolean)request.getSession().getAttribute("employees_loggedin") )
{
	response.sendRedirect("employeeslogin.jsp");
}
	%>
	<div class="container">
		<div class="row" align="center" style="margin-top: 100px;">
			<div class="col-lg-4 col-lg-offset-4 centered">
				<form name="addstar" action="addStar.jsp" method="GET">
					<input type="submit" value="Add Star" id="login_button" />
				</form>
			</div>
		</div>
	</div>
	
	
	<div class="container">
		<div class="row" align="center" style="margin-top: 100px;">
			<div class="col-lg-4 col-lg-offset-4 centered">
				<form name="showmeta" action="ShowMeta" method="GET">
					<input type="submit" value="Show Metadata" id="login_button" />
				</form>
			</div>
		</div>
	</div>


<div class="container">
		<div class="row" align="center" style="margin-top: 100px;">
			<div class="col-lg-4 col-lg-offset-4 centered">
				<form name="addmovie" action="addMovie.jsp" method="GET">
					<input type="submit" value="Add Movie" id="login_button" />
				</form>
			</div>
		</div>
	</div>

</body>
</html>