
<%@ page import="data_beans.*" %>

<%
	String welcomeMessage = "Please Sign In";
	if ((Customer) request.getSession().getAttribute("customer_loggedin") != null)
	{
		Customer customer = (Customer) request.getSession().getAttribute("customer_loggedin");
		welcomeMessage = "Welcome " + customer.getFirst_name() + " " + customer.getLast_name();
	}
%>
<link href="Style/master.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Futura:300,400,500' rel='stylesheet' type='text/css'>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="javascript/search_autocomplete.js"></script>


<style>
#searchForm{
position: absolute;
    left: 100px;
    top: 65px;
    width: 300px;
    height:30px;}
</style>


<body>
	<div class="page-header">
	<div id="header"></div>
		<div id="searchForm">
		<input type="text" name="search" size="50" id="searchBar" value="" placeholder="Search ..." onkeyup="lookup(this.value)"/>
	</div>
	<div id="info">
		<p id="welcome_message_text" align="center"><%=welcomeMessage%><p>
		<ul>
  			<li><a href="FabFlixMain"><img src="Images/home.png" style="width:25%; position: relative; margin-left:10px"/></a>
  			<li><a href="FabFlixSessionCart.jsp"><img src="Images/shopping_cart.png" style="width:30%; position: relative;" /></a>
  			</li>
  			<li><a href="Logout"><img src="Images/logout.png" style="width:25%; position: relative;" /></a></li>
		</ul>
		<br/>
	</div>
	</div>
</body>