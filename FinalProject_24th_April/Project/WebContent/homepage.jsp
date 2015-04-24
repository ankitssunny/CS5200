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
        	<form action="LogOut" method="post">
            <input type="submit" value="Logout">
        	</form>
        </div>
         
        <div class="searches">
        	<form action="./searchUser.jsp" method="post">
            <input type="text" name="searchuser"> <input type="submit" value="Search Usernames">
            </form>
        
    		<form action="./QueryResults.jsp" method="post" >
            <input type="text" name="keyword"> <input type="Submit" value="Search Products">
        	</form>
        	
        	<form action="./SearchCommunities.jsp" method="post" >
            <input type="text" name="cname"> <input type="Submit" value="Search Communities">
        	</form>
    		<form action="./CreateCommunity.jsp" method="post" ><input type="Submit" value="Create Community">
        	</form>
    	
    	<br><br><br>
    	</div>
    	<table>
    	<tr><td>
    	<div class= "friends">
    	<%
    	friendsDAO frnd = new friendsDAO();
    	List<friends> friends = frnd.findFriendsForUser(user.getUsername());%>
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
    	List<User_Communities> uc = udao.findCommunitiesForUser(userName);%>
    	<h3>Communities</h3>
    	<table style="width:20%">
    	<% for (User_Communities u: uc){%>
        <tr><td><% out.println(u.getCname());%></td></tr>
    	<%}%>   	
    	</table></div>
    	</td>
    	<td>
    	<div class="friends">
    	<%
    	LikeDAO ldao = new LikeDAO();
    	List<Likes> li = ldao.findLikesForUser(userName);%>
    	<h3>Products Liked By User</h3>
    	<table style="width:20%">
    	<% for (Likes u: li){%>
        <tr><td><% out.println(u.getItemname());%></td></tr>
    	<%}%>   	
    	</table></div>
    	<td>
    	 <div class="friends"><%
       	CommentDAO cdao = new CommentDAO();
       	List<comment> lc = cdao.findCommentsByCommenter(userName);%>
       	<h3>Comments Made</h3>
       	<table style="width:20%">
       	<% for (comment u: lc){%>
           <tr><td><% out.println(u.getContent());%></td></tr>
       	<%}%>   	
       	</table></div>
       	</td></tr></table>
</body>
</html> 