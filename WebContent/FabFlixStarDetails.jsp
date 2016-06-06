<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Star Details</title>


<link href='http://fonts.googleapis.com/css?family=Raleway:300,400,500' rel='stylesheet' type='text/css'>
<style>
a,p,button,td,input,span,h3,h4{font-family:Futura;color:black;text-decoration:none"}
table {border: 0px;}
table tr > td {border: 0px;}
</style>
</head>
	<body>
	
	<%
		if ((Customer) request.getSession().getAttribute("customer_loggedin") == null)
		{
			response.sendRedirect("login.jsp");
		}
	%>
	
	<%@ include file="NavBar.jsp"%>
	
		<div style="position: relative;top: 200px;left: 200px;">
		
		<img src="${star.photo_url}" alt="No Star Image">
		<h3>Star: ${star.first_name} ${star.last_name}</h3>
		<h4>Born: ${star.dob}</h4></br>
		<h4>Also acted in: </h4>
		<table>
			<c:forEach items="${movies}" var="movie">
				<tr> 
			  		<td>
			  			<p>
							<a href="FabFlixMovieDetails?movieid=${movie.id}">${movie.title} - ${movie.year}</a>
						</p>
			  		</td>
				</tr>
			</c:forEach>
		</table>
		
		</div>
	
	</body>
</html>