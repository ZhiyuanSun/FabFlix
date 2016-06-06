<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="data_beans.*" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Movie List</title>
</head>
<body>


<%
	if ((Customer) request.getSession().getAttribute("customer_loggedin") == null)
	{
		response.sendRedirect("Login.jsp");
	}
%>

<%@ include file="NavBar.jsp"%>

<h2 style="position: absolute; top:350px; left:450px; font-family:raleway; ">Check-out has completed!</h2>
