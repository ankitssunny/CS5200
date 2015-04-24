<%@page import="neu.edu.Project.API.*,neu.edu.Project.Entity.*,neu.edu.Project.DAO.*" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="neu.edu.Project.API.*, neu.edu.Project.Entity.*, java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
  <%
	//UserDAO dao = UserDAO.getInstance();
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
<title><%= user.getUsername()%>'s Personal Information</title>
</head>
<style type="text/css">

body {
    background-image: url('http://img.mota.ru/upload/wallpapers/2015/03/09/14/00/43439/002-1366x768.jpg');
}
.labelClass{
    float: left;
    width: 150px;
    text-shadow: 1px 1px 1px #000
  }
</style>
<body>
<span class="labelClass">Username: <%= user.getUsername()%></span><br> <br>
<span class="labelClass">Enter New Values for the Details given below.</span> <br> <br>
<div align="left">
    <br>
        <form action="Update" method="post">
            <span class="labelClass">Password:</span><input type="password" name="newpassword"> <br>
            <span class="labelClass">Address:</span><input type="text" name="address"> <br>
            <span class="labelClass">First Name:</span><input type="text" name="fname"> <br>
            <span class="labelClass">Last Name:</span><input type="text" name="lname"> <br>
            <span class="labelClass">Contact #:</span><input type="text" name="contact"> <br>
            <span class="labelClass">Email:</span><input type="text" name="email"> <br>
            <span class="labelClass">Credit Card #:</span><input type="text" name="credit"> <br>
            <input type="hidden" name="username" value=<%= user.getUsername()%>>
            <input type="submit" value="Update Information">
        </form>
    </div>
</body>
</html>