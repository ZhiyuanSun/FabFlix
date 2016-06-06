<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Movie List</title>

<link href="Style/boostrap.min.css" rel="stylesheet"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="javascript/tooltip.js"></script>

<style>

a,p,button,td,input,span,h3,h4 {font-family:Futura;}
img.resize_arrow {width:10px;}
a {color:Black;}
a:hover {color:Gray;}
a .movieDetails {font-family:Futura;}
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

<div class="container">
<div class="row">
  <div class="span7 offset5">
		<div style="position: relative;top: 200px;left: 100px;">
		
			<p style="font-family:Futura;">
				Sort By:&nbsp;&nbsp;
				<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page}&sort=titleasc">Title <img src="Images/up_arrow.png" class="resize_arrow" /></a> | 
		 		<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page}&sort=titledsc">Title <img src="Images/down_arrow.png" class="resize_arrow" /></a> |
				<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page}&sort=yearasc">Year <img src="Images/up_arrow.png" class="resize_arrow" /></a> |
				<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page}&sort=yeardsc">Year <img src="Images/down_arrow.png" class="resize_arrow" /></a>
			</p>
		
			<table border=1 id="results">
				<c:forEach items="${movies}" var="movie">
				<tr>
					<td>
						<img src="${movie.banner_url}" width="150" height="200" alt="No Movie Image"/>
					</td>
					<td>
						<h3 class="movieCardAnchor" id="${movie.id}">Title: <a href="FabFlixMovieDetails?movieid=${movie.id}">${movie.title}</a></h3>
						<p>Year Released: ${movie.year}</p>
						<p>Director: ${movie.director}</p>
						<p>Stars: 
							<c:forEach items="${movie.stars}" var="star">
								<a href="FabFlixStarDetails?starid=${star.id}">${star.first_name} ${star.last_name}</a>, 
							</c:forEach>
						</p>
						<p>Genres:
							<c:forEach items="${movie.genres}" var="genre">
								${genre.name}, 
							</c:forEach>
						</p>
						<p><a href="${movie.trailer_url}">Watch Trailer</a></p>
					</td>
				</tr>
				<tr>
					<td>
						<form name="addMovieToCart" action="CartManager?request=add_item&quantity=1&movieid=${movie.id}" method="POST">
							<button type="submit" class="button_submit" class="btn btn-primary btn-block">Add to Cart</button>
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			</br></br></br>
		
			<c:if test="${page ne 0}">
				<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page - 1}&sort=${sort}">
					<button>Previous</button>
				</a>
			</c:if>
		
			<c:if test="${page lt maxPage}">
				<a href="FabFlixMovieList?${queries}lim=${lim}&page=${page + 1}&sort=${sort}">
					<button>Next</button>
				</a>
			</c:if>
			
			<div align="center" style="padding-bottom:15px;">
				<p>
					Results Per Page:  <!-- 10, 25, 50, 100  -->
					<a href="FabFlixMovieList?${queries}lim=5&page=0&sort=${sort}">5</a>
					<a href="FabFlixMovieList?${queries}lim=10&page=0&sort=${sort}">10</a>
					<a href="FabFlixMovieList?${queries}lim=25&page=0&sort=${sort}">25</a>
					<a href="FabFlixMovieList?${queries}lim=50&page=0&sort=${sort}">50</a>
					<a href="FabFlixMovieList?${queries}lim=100&page=0&sort=${sort}">100</a>
				</p>
			</div>
		</div>
</div>
</div>
</div>
</body>
</html>