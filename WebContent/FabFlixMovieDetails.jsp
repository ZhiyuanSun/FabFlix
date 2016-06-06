<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Movie Details</title>

<link href='http://fonts.googleapis.com/css?family=Futura:300,400,500' rel='stylesheet' type='text/css'>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="javascript/tooltip.js"></script>
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

<div style="margin-top:20px;position: absolute;top: 150px;left: 150px;">
	<table>
  		<tr>
    		<td>
				<img src="${movie.banner_url}" width="150" height="200" alt="No Movie Image"/>
			</td>
			<td>
				<h3 id="${movie.id}">Title: ${movie.title}</h3>
				<p>Year Released: ${movie.year}</p>
				<p>Director: ${movie.director}</p>
				<p>Stars: 
					<c:forEach items="${stars}" var="star">
						<a href="FabFlixStarDetails?starid=${star.id}">${star.first_name} ${star.last_name}</a>, 
					</c:forEach>
				</p>
				<p>Genres:
					<c:forEach items="${genres}" var="genre">
						${genre.name}, 
					</c:forEach>
				</p>
				<p>
					<a href="${movie.trailer_url}">Watch Trailer</a>
				</p>
    		</td>
    	</tr>
  		<tr>
    		<td>
				<form name="addMovieToCart" action="CartManager?request=add_item&quantity=1&movieid=${movie.id}" method="POST">
					<button type="submit">Add Movie to Cart</button>
				</form>
    		</td>
  		</tr>
	</table>
</div>

</body>
</html>