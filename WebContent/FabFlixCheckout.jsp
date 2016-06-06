<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Checkout</title>
<link href="Style/boostrap.min.css" rel="stylesheet"/>

<style>
a,p,button,td,input,span{font-family:Futura;"}
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

<div class="container" style="margin-top:30px;margin-right:50px;">
<div class="row">
  <div class="span7 offset5">
	<div style="position: absolute;top: 200px;left:350px;font-family: Arial;">
		<div>FabFlix Checkout</div><br>
			<div style="font-size:20px; margin-left: auto;margin-right: auto; width: 20em; ">
				<form name="Checkout" action="Checkout" method="POST">
					<span id="form1">Credit Card Number<br></span>
				<input type="text" name="cc_id" size="50" id="cc_id" maxlength="20" value="" required/><br>
					<span id="form2">Name on Card<br></span>
				<input type="text" name="first_name" size="22" id="first_name" value="" required/>*First</br>
				<input type="text" name="last_name" size="22" id="last_name" value="" required/>*Last</br>
					<span id="form3">Expiration Date (YYYY-MM-DD)<br></span>
				<input type="text" name="expiration" size="50" id="expiration" maxlength="10" value="" required/><br><br>
				<input type="submit" name="Search Submit" value="Submit"/>
				<h3>${checkout_fail}</h3>
				</form>
			</div>
	</div>
</div>
</div>
</div>

</body>