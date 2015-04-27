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
	if (cookies != null){
		for (Cookie cookie : cookies){
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
	<h1>Search Results</h1>
	<h2>Perfect Place to shop and socialize.</h2>
	<ul>
		<li><a href="./homepage.jsp">Go Home</a></li>
		<li><a href="EditInfo.jsp"> Edit Info</a></li>
   </ul>
	<p id="layoutdims">Our Partners <a href="http://www.ebay.com/">eBay</a> | <a href="https://www.facebook.com/?_rdr">Facebook</a></p>
</div>
<div class="colmask threecol">
	<div class="colmid">
		<div class="colleft">
			<div class="col1">
				<!-- Column 1 start -->
				<%
					CommunityDAO commDAO = new CommunityDAO();
					String searchCommunity = request.getParameter("cname");
					Community comm = commDAO.findCommunity(searchCommunity);
					if (userName == null || comm == null) {
							System.out.print("The community does not exist.");
							response.sendRedirect("homepage.jsp");
					}
					if (userName != null && comm != null) {
				%>
			
				
				<h3>Welcome. Below are your details</h3>
				<p> Name: <%=user.getFirstname() %></p>
              	<p> Address: <%=user.getAddress() %></p>
              	<p> Contact: <%=user.getContact() %></p>
              	<form action="LogOut" method="post">
           	 	<input type="submit" value="Logout" />
            	</form>
				<table >
   				<h3>Community Details</h3>
   				<tr >
   						<td>Name</td>
   						<td><% comm.getName(); %></td></tr>
   				<tr >
   						<td>Creator</td>
   						<td><%comm.getCreator();%></td></tr>
   				<tr >
   						<td>Description</td>
   						<td><% comm.getDescription();%></td></tr>				
   						</table> 
				
				<%
			   	UserCommunityDAO ucdao = new UserCommunityDAO();
			   	//Checking If user is a member of the community
				if (ucdao.usercommunityexists(userName, comm.name) == false)		
			   	{%>
			   
			   	<form action = "UserCommunity" method="post">
				<input name="community" type="hidden" value=<%= comm.name %>>
				<input type="submit" value="Join Community">   
			   	</form>	
			   	<%} 
			   	else
			   	{%>
	   				<form action = "UserCommunity" method="post">
					<input name="community" type="hidden" value=<%= comm.name %>>
					<input type="submit" value="Leave Community">   
	   				</form>	
	   				
	   				<form action = "AddComment" method="post">
						<input name="community" type="hidden" value=<%= comm.name %>>
						<input name="comment" type="text" > 
						<input type="submit" value="Add Comment">   
	   					</form>	
			   	<%} 
			   	
			   	//Checking if this user is Owner of the Community
			   	CommunityDAO cdao = new CommunityDAO();
			   	if(cdao.verifyCommunityOwner(userName, comm.name) == true)
			   	{%>
			   		<h2>You are the Admin of the Community</h2>
			   		<form action = "RemoveCommunity" method="post">
						<input name="community" type="hidden" value=<%= comm.name %>>
						<input type="submit" value="Remove Community">   
	   				</form>	
			   		
			   	<%}
			   	%>
											
									         
				<!-- Column 1 end -->
				</div>
				<div class="col2">
				<!-- Column 2 start -->
				<h2>Members of Community</h2>
				<%
				UserCommunityDAO udao = new UserCommunityDAO();
				List<User_Communities> uc = udao.findMembersInCommunity(comm.name);%>
				<h3>Members</h3>
				<table style="width:20%">
				<% for (User_Communities uu: uc){%>
				<tr><td><a href="ViewFriend?username=<%=uu.getName()%>">
				<% out.println(uu.getName());%></a></td></tr>
				<%}%>
				</table>
				<br /><br /><br />
				<!-- Column 2 end -->
			</div>
			<div class="col3">
				<!-- Column 3 start -->
				<h2>Comments made in Community</h2>
				<%
				CommentDAO cmnt = new CommentDAO();
				List<comment> lc = cmnt.findCommentsByCommunity(comm.name);%> 
				<table style="width:20%">
				<tr><td>Comment</td><td>Made By</td></tr>
				<% for (comment uu: lc){%>
				<tr>
				<td><% out.println(uu.getContent());%></td>
				<td><a href="ViewFriend?username=<%=uu.getCommenter()%>">
				<% out.println(uu.getCommenter());%></a></td>
				</tr>
				<%}
				}%>
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