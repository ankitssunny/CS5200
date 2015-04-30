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
		UserDAO dao = new UserDAO();
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
	 	 
	   	String searchuser = request.getParameter("searchuser");
		User searchedUser = dao.findUser(searchuser);
		if (searchedUser == null || userName.equals(searchuser))
		{
				response.sendRedirect("homepage.jsp");
		}%>
	
</head>
<body>

<div id="header">
	<h1>Search Results</h1>
	<h2>Perfect Place to shop and socialize.</h2>
	<ul>
		<li><a href="./homepage.jsp">Go Home</a></li>
		<li><a href="EditInfo.jsp"> Edit Info</a></li>
		<li><a href="Cart.jsp"> My Cart</a></li>
		
   </ul>
	<p id="layoutdims">Our Partners <a href="http://www.ebay.com/">eBay</a> | <a href="https://www.facebook.com/?_rdr">Facebook</a></p>
</div>
<div class="colmask threecol">
	<div class="colmid">
		<div class="colleft">
			<div class="col1">
				<!-- Column 1 start -->
				<h3>Welcome. Below are your details</h3>
				<p> Name: <%= user.getUsername() %></p>
              	<p> Address: <%= user.getAddress() %></p>
              	<p> Contact: <%= user.getContact() %></p>
              	<form action="LogOut" method="post">
           	 		<input type="submit" value="Logout">
            	</input></form>
            	
				<h3>Below are the Details of <%= searchedUser.getUsername()%> </h3>
    			<table>
   				<tr>
   						<td>Username</td>
   						<td><%= searchedUser.getUsername()%></td></tr>
   				<tr>
   						<td>Name</td>
   						<td><%= searchedUser.getFirstname()%> </td></tr>
   				<tr>
   						<td>Contact</td>
   						<td><%= searchedUser.getContact()%> </td></tr>
   				<tr>
   						<td>Email</td>
   						<td><%= searchedUser.getEmail()%> </td></tr>				
   				<tr>
   						<td>Address</td>
   						<td><%= searchedUser.getAddress()%> </td></tr>
   						</table>
			   	<% 
		   	   // Checking if searched user is in the friends list of this yser or not
		   	  friendsDAO frndDAO = new friendsDAO();		
   	   		   if (userName.equals(searchedUser.getUsername())) {
		   		  response.sendRedirect("homepage.jsp");
		   	  }
		   	  
		   	  else{
		   		  if(frndDAO.friendshipexists(userName, searchedUser.getUsername()) == false)
		   		  {%>
		   		  		<form method="post" action = "AddFriend" name="inputpage">
						<input name="hidden" type="hidden" value=<%= searchedUser.getUsername() %>>
						<input TYPE="submit" Value="Add Friend" NAME="submit" title="add">   
		   				</form>	 
		   				<%			
		   		  }
		   		  else
		   		  {
		   			  %>
		   			  	<form method="post" action = "AddFriend" name="inputpage">
						<input name="hidden" type="hidden" value=<%= searchedUser.getUsername() %>>
						<input TYPE="submit" Value="Remove Friend" NAME="submit" title="remove">   
		   				</form>
		   				<%	
		   		  }
		   		}
		   	
		   				%> 
		   						         
						<!-- Column 1 end -->
				</div>
				<div class="col2">
				<!-- Column 2 start -->
				<h2>Friends</h2>
				<%
		        friendsDAO frnd = new friendsDAO();
		        List<friends> friends = frnd.findFriendsForUser(searchedUser.getUsername());%>
		        <table style="width:20%">
		        <% for (friends f:friends) {%>
		        <tr><td><a href="ViewFriend?username=<%= f.getFriend()%>">
		                <% out.println(f.getFriend()); %>
		        </a></td></tr>
		        <%}%></table>
				<br /><br /><br />
				<h2>Products Liked</h2>
				<%
		        LikeDAO ldao = new LikeDAO();
		        List<Likes> li = ldao.findLikesForUser(searchedUser.getUsername());%>
		        <table style="width:20%">
		        <% for (Likes u:li){ %>
		        <tr><td><% out.println(u.getItemname()); %></td></tr> 
		        <%}%>
		        </table>
				<!-- Column 2 end -->
			</div>
			<div class="col3">
				<!-- Column 3 start -->
				<h2>Communities</h2>
				<%
		        UserCommunityDAO udao = new UserCommunityDAO();
		        List<User_Communities> uc = udao.findCommunitiesForUser(searchedUser.getUsername());%>
		        <table style="width:20%">
		        <% for (User_Communities u: uc){ %>
		        <tr><td><a href="ViewCommunity?commname=<%= u.getCname()%>"><% out.println(u.getCname()); %>
		        </a></td></tr>
		        <%} %>
		        </table>
				<h2>Comments</h2>
				<% 
		        CommentDAO cdao = new CommentDAO();
		        List<comment> lc = cdao.findCommentsByCommenter(searchedUser.getUsername());%>
		        <table style="width:20%">
		        <tr><td>Comment</td>
		        <td>Community</td></tr>
		        <% for (comment u: lc){ %>
		        	<tr>
		        		<td><% out.println(u.getContent()); %></td>
		        		<td><a href="ViewCommunity?commname=<%= u.getCommunity()%>"><% out.println(u.getCommunity()); %>
		        </a></td></tr>
		        	<%} %>
		        </table>
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

