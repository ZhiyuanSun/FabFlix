<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Style/boostrap.min.css" rel="stylesheet"/>
<title>FabFlix Shopping Cart</title>

<style>
a,p,button,td{font-family:Futura;"}
body {font-family: Futura;}
table {width: 870px;}
table tr > td {padding-bottom: 20px;}
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

	<div class="container" align="center" style="margin-top:30px;">
		<div class="row">
			<div class="span7 offset5">
			<div style="position: relative;top: 150px;left: 150px;">
					<table width="100%">
						<tr>
							<td><h3 style="float: left;">Movie</h3></td>
							<td><h3 style="float: left;">Quantity</h3></td>
						</tr>
						<c:forEach items="${session_cart.cartItems}" var="cartItem">
							<tr>
								<td>${cartItem.movie.title}</td>
								<td>
									<form method="POST" action="CartManager">
										<input type="text" name="quantity" value="${cartItem.quantity}" style="width: 40px;"/>
										<input type="hidden" name="movieid" value="${cartItem.movie.id}"/>
										<button name="request" value="update_item_quantity" type="submit"">Update Quantity</button>
										<button name="request" value="remove_item" type="submit"">Remove</button>
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td><a href="FabFlixCheckout.jsp"><button class="button_submit">Checkout</button></a></td>
							<td>
								<form method="POST" action="CartManager">
									<input type="hidden" name="movieid" value="${cartItem.movie.id}"/>
									<button name="request" value="remove_all_items" type="submit">Empty Cart</button>
								</form>
							</td>
						</tr>
					</table>
			</div>
			</div>
		</div>
	</div>
</body>
</html>