<%@page import="neu.edu.Project.API.*,neu.edu.Project.Entity.*,neu.edu.Project.DAO.*, java.sql.*, java.util.*" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
  <%
	UserDAO dao = new UserDAO();
	User user = new User();
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
    	for (Cookie cookie : cookies) {
    		if (cookie.getName().equals("User")) {
                userName = cookie.getValue();
            	user = dao.findUser(userName);
            	break;
            }
        }
    }
    if (userName == null)
        response.sendRedirect("login.html");
	%>
<title><%= user.getUsername()%>'s Shopping Portal</title>
</head>
<body>
<h1><%= user.getUsername()%>'s HomePage</h1>
		<div class ="userinfo">
       		<p> Name: <%= user.getFirstname() %></p>
      		<p> Address: <%= user.getAddress() %></p>
       		<p> Contact: <%= user.getContact() %></p>
        	<a href="EditInfo.jsp">Edit Personal Information</a>
        	<a href= "./homepage.jsp"> Go Home</a>
        	<form action="LogOut" method="post">
            <input type="submit" value="Logout">
        	</form>
        </div>
    <div align="left">
    <br>
        <form action="CreateCommunity" method="post">
        	<p>Please Enter the Details for the Community you want to create.</p>
            <span class="labelClass">Community Name:</span><input type="text" name="cname"> <br>
            <span class="labelClass">Description:</span><input type="text" name="description"> <br>
            <input type="submit" value="Create Community">
        </form>
    </div>
</body>
</html>