<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="neu.edu.Project.API.*,neu.edu.Project.Entity.*,neu.edu.Project.DAO.*, java.sql.*, java.util.*, java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-GB">
<head>
	<title>Welcome to Social Cart Website</title>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
	<meta name="description" content="The Perfect 3 Column Liquid Layout: No CSS hacks. SEO friendly. iPhone compatible." />
	<meta name="keywords" content="The Perfect 3 Column Liquid Layout: No CSS hacks. SEO friendly. iPhone compatible." />
	<meta name="robots" content="index, follow" />
	<link rel="stylesheet" type="text/css" href="css/style.css" media="screen"  />
	<link rel="stylesheet" type="text/css" href="ccs/style.css"/>

	<%	
		UserDAO dao= new UserDAO();
		User user = new User();
		String userName = null;
	 	 Cookie[] cookies = request.getCookies();
	 	 if (cookies !=null) {
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

</head>
<body>

<div id="header">
	<h1><%= userName %>'s Shopping Portal</h1>
	<h2>Create New Community</h2>
	<ul>
		<li><a href="homepage.jsp"> Home</a></li>
		<li><a href="EditInfo.jsp"> Edit Info</a></li>
		
	</ul>
	<p id="layoutdims">Our Partners <a href="http://www.ebay.com/">eBay</a> | <a href="https://www.facebook.com/?_rdr">Facebook</a></p>
</div>
<div class="colmask threecol">
	<div class="colmid">
		<div class="colleft">
			<div class="col1">
				<!-- Column 1 start -->
				<p> Name: <%= user.getFirstname() %></p>
				<p> Address: <%= user.getAddress() %></p>
				<p> Contact: <%= user.getContact() %></p>
				<form action="LogOut" method="post">
				<input type = "submit" value="Logout">
				</input>
				</form>
				
				<!-- Creating a New Community -->
				<form action="CreateCommunity" method="post">
				<p>Please Enter the Details for the Community you want to create.</p>
				<span class="labelClass">Community Name:</span><input type="text" name="cname"> <br>
				<span class="labelClass">Description:</span><input type="text" name="description"> <br>
				<input type = "submit" value="Create Community">
				</input></br></form>
				
				
				<!-- Column 1 end -->
				</div>
				<div class="col2">
				<!-- Column 2 start -->
				<!-- Column 2 end -->
			</div>
			<div class="col3">
				<!-- Column 3 start -->
						<!-- Column 3 end -->
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>This page has been created by Ankit Sharma and Dishant Shah as part of CS5200 Course Project</p>
</div>
</body>
</html>

