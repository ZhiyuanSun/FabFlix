<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Search</title>
	
	<link href="Style/boostrap.min.css" rel="stylesheet"/>
	<link href='http://fonts.googleapis.com/css?family=Futura:300,400,500' rel='stylesheet' type='text/css'>
	
	</head>
	
	<body>
	
	<%
		if ((Customer) request.getSession().getAttribute("customer_loggedin") == null)
		{
			response.sendRedirect("login.jsp");
		}
	%>
	
	<%@ include file="NavBar.jsp"%>
	
	</br>
	</br>
	</br>
	
		<div>
		
			<div style="text-align:center;font-size:40px;margin-top:140px;"><h2 style="font-family:futura;font-size:26px">SEARCH OPTIONS</h2></div>
		
				<div class="form-group" style="font-size:20px;margin-left: auto;margin-right: auto; margin-top:40px; width: 20em;">
					<form name="Search" action="FabFlixMovieList" method="GET">
					<label for="usr" style="font-family:Futura">Title:</label>
					<input type="text" name="title" size="50" value=""/><br>
					<label for="usr" style="font-family:Futura">Year:</label>
					<input type="text" name="year" size="50" value=""/><br>
					<label for="usr" style="font-family:Futura">Director:</label>
					<input type="text" name="director" size="50" value=""/><br>
					<label for="usr" style="font-family:Futura">Star Name:</label></br>
					<input type="text" name="first_name" size="22" value=""/>*First
					<input type="text" name="last_name" size="22" value=""/>*Last<br>
					<input type="checkbox" name="substring_match" value="true">
					<label for="usr" style="font-family:Futura">Substring Matching:</label><br>
					<br>
					<input type="hidden" name="from_search" value="true"/>
					<input type="submit" name="Search Submit" value="Submit" class="button_submit"/>
					</form>
				</div>
		
		</div>
	
	</body>
</html>