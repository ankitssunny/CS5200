<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="neu.edu.Project.API.*,neu.edu.Project.Entity.*,neu.edu.Project.DAO.*, java.sql.*, java.util.*, java.io.PrintWriter"%>
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
      
      String searchuser = (String)session.getAttribute("name");
	  UserDAO usr = new UserDAO();
	  User u= usr.findUser(searchuser);
      System.out.print(searchuser);
	  System.out.print(userName);
     
%>
<title><%= userName%>'s Friend</title>
</head>
 
<body>
<h1><%= u.getUsername()%>'s Profile</h1>
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
 <div>
 		<table id="friendDetails"> 
 			<caption>Friend Details</caption>
    	        <tr class="alt1">
    	        	<td>Username</td>
    	        	<td><%= u.getUsername()%></td></tr>
    	        <tr class="alt">
    	        	<td>Name</td>
    	        	<td><%= u.getFirstname()%></td></tr>
    	        <tr class="alt1">
    	        	<td>Contact</td>
    	        	<td><%= u.getContact()%></td></tr>
    	        <tr class="alt">
    	        	<td>Email</td>
    	        	<td><%= u.getEmail()%></td></tr>
    	        <tr class="alt1">
    	        	<td>Address</td>
    	        	<td><%= u.getAddress()%></td></tr>
    	</table>        	
</div>


<table>
    	<tr><td>
    	<div class= "friends">
    	<%
    	friendsDAO frnd = new friendsDAO();
    	List<friends> friends = frnd.findFriendsForUser(u.getUsername());%>
    	<h3>Friends</h3>
    	<table style="width:20%">
    	<% for (friends f: friends){%>
        <tr><td><a href="ViewFriend?username=<%=f.getFriend()%>">
        <% out.println(f.getFriend());%>
        </a></td></tr>
    	<%}%>
    	</table>
    	</div>
    	</td>
    	<td>
       	<div class="friends">
    	<%
    	UserCommunityDAO udao = new UserCommunityDAO();
    	List<User_Communities> uc = udao.findCommunitiesForUser(u.getUsername());%>
    	<h3>Communities</h3>
    	<table style="width:20%">
    	<% for (User_Communities uu: uc){%>
        <tr><td><% out.println(uu.getCname());%></td></tr>
    	<%}%>   	
    	</table></div>
    	</td>
    	<td>
    	<div class="friends">
    	<%
    	LikeDAO ldao = new LikeDAO();
    	List<Likes> li = ldao.findLikesForUser(u.getUsername());%>
    	<h3>Products Liked By User</h3>
    	<table style="width:20%">
    	<% for (Likes lu: li){%>
        <tr><td><% out.println(lu.getItemname());%></td></tr>
    	<%}%>   	
    	</table></div>
    	<td>
    	 <div class="friends"><%
       	CommentDAO cdao = new CommentDAO();
       	List<comment> lc = cdao.findCommentsByCommenter(u.getUsername());%>
       	<h3>Comments Made</h3>
       	<table style="width:20%">
       	<% for (comment cu: lc){%>
           <tr><td><% out.println(cu.getContent());%></td></tr>
       	<%}%>   	
       	</table></div>
       	</td></tr></table>
</body>
</html>