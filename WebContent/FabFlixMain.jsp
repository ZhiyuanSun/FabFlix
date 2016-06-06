<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="data_beans.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>FabFlix Main</title>
   <link href="Style/boostrap.min.css" rel="stylesheet"/>      
   <link href='http://fonts.googleapis.com/css?family=Raleway:300,400,500' rel='stylesheet' type='text/css'>

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
	</br>
	</br>
	</br>
	</br>
	</br>
	
		<div class="row" align="center">
		  <div class="span9">
		    <h2 style="font-family:futura;">SEARCH OPTIONS</h2>
		    <div class="row" style="border:ridge;">
		      <div class="span6">
		         <form name="browse_genre" action="FabFlixMain" method="POST">
		                <p><b>Browse by Genre</b></p>
		                   <select name="browse_genre_select">
		                    <c:forEach var="genre" items="${genres}">
		                     <option value="${genre}">${genre}</option>
		                    </c:forEach>
		                   </select>
		                   
		                  <input type="submit" name="browse_title_submit" value="Go" />
		         </form>
		      </div>
		</br>
		      <div class="span3">
		         <form name="browse_title" action="FabFlixMain" method="POST">
		         	<p><b>Browse by Title</b></p>
		           		<select name="browse_title_select">
		           			<option value="0">0</option>
				            <option value="1">1</option>
				            <option value="2">2</option>
				            <option value="3">3</option>
				            <option value="4">4</option>
				            <option value="5">5</option>
				            <option value="6">6</option>
				            <option value="7">7</option>
				            <option value="8">8</option>
				            <option value="9">9</option>
				            <option value="A">A</option>
				            <option value="B">B</option>
				            <option value="C">C</option>
				            <option value="D">D</option>
				            <option value="E">E</option>
				            <option value="F">F</option>
				            <option value="G">G</option>
				            <option value="H">H</option>
				            <option value="I">I</option>
				            <option value="J">J</option>
				            <option value="K">K</option>
				            <option value="L">L</option>
				            <option value="M">M</option>
				            <option value="N">N</option>
				            <option value="O">O</option>
				            <option value="P">P</option>
				            <option value="Q">Q</option>
				            <option value="R">R</option>
				            <option value="S">S</option>
				            <option value="T">T</option>
				            <option value="U">U</option>
				            <option value="V">V</option>
				            <option value="W">W</option>
				            <option value="X">X</option>
				            <option value="Y">Y</option>
				            <option value="Z">Z</option>
				        </select>
		           	<input type="submit" name="browse_genre_submit" value="Go"/>
		         </form>
		      </div>
		      </br>
		      </br>
		      <div class="span9">
		      		*Advanced Search
		    		<a href="Search.jsp"><img src="Images/advanced_search.png" style="border: 0px; height: 80px;" /></a>
		  	  </div>
		      
		    </div>
		  </div>
		</div>
	
	</body>
</html>