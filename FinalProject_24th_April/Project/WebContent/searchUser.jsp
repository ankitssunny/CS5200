<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="neu.edu.Project.API.*,neu.edu.Project.Entity.*,neu.edu.Project.DAO.*, java.sql.*, java.util.*, java.io.PrintWriter"%>
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
<title><%= user.getUsername()%>'s Shopping Portal</title>
</head>
<body>    	
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


    <div class="container">
        <%
       		String searchuser = request.getParameter("searchuser");
            User searchedUser = dao.findUser(searchuser);
            friendsDAO frndDAO = new friendsDAO();
            if (searchedUser == null)
            {
            	response.sendRedirect("homepage.jsp"); 	
            }
            else
            {%>
           <div>
 				<table id="friendDetails"> 
    	        <tr class="alt1">
    	        	<td>Username</td>
    	        	<td><%= searchedUser.getUsername()%></td></tr>
    	        <tr class="alt">
    	        	<td>Name</td>
    	        	<td><%= searchedUser.getFirstname()%></td></tr>
    	        <tr class="alt1">
    	        	<td>Contact</td>
    	        	<td><%= searchedUser.getContact()%></td></tr>
    	        <tr class="alt">
    	        	<td>Email</td>
    	        	<td><%= searchedUser.getEmail()%></td></tr>
    	        <tr class="alt1">
    	        	<td>Address</td>
    	        	<td><%= searchedUser.getAddress()%></td></tr>
    			</table>        	
			</div>
       
       
           <% 
           //Checking if the Searched User is in the Friends List of this User or not
           if (userName.equals( searchuser)){
        	   
           	response.sendRedirect("homepage.jsp"); 	
              
           }
           else{   
            if (frndDAO.friendshipexists(userName, searchuser) == false)
            {%>
            	<form method="post" action="AddFriend" name="inputpage">
            	<input name="hidden" type="hidden" value=<%=searchuser%>>
            	<INPUT TYPE="submit" Value="Add Friend" NAME="submit" title="add">
            	</form>
            	<%
            } 
            else {
            	%> 
            	<form method="post" action="AddFriend" name="inputpage">
            	<input name="hidden" type="hidden" value=<%=searchuser%>>
            	<INPUT TYPE="submit" Value="Remove Friend" NAME="submit" title="remove">
            	</form>
            	<%
            	}
            	}
           }
            	%>
            
        
            
    </div>
</body>
</html>