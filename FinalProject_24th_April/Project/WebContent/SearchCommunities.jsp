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
    if (userName == null)
        response.sendRedirect("login.html");
	%>
<title><%= user.getUsername()%>'s Shopping Portal</title>
</head>
<body>    	
    <div class="container">
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
        
        <div class ="userinfo">
		      		<p> Name: <%= user.getFirstname() %></p>
		     		<p> Address: <%= user.getAddress() %></p>
		      		<p> Contact: <%= user.getContact() %></p>
			       	<a href="EditInfo.jsp">Edit Personal Information</a>
			       	<form action="LogOut" method="post">
			       	<a href= "./homepage.jsp"> Go Home</a>
			        <input type="submit" value="Logout">
		       		</form>
		</div>
		    
        <div>
 			<table id="friendDetails"> 
    	        <tr class="alt1">
    	        	<td>Name</td>
    	        	<td><%= comm.name%></td></tr>
    	        <tr class="alt">
    	        	<td>Creator</td>
    	        	<td><%= comm.creator%></td></tr>
    	        <tr class="alt1">
    	        	<td>Description</td>
    	        	<td><%= comm.description%></td></tr>
    		</table>        	
		</div>
            <%
            
            UserCommunityDAO ucdao = new UserCommunityDAO();
            System.out.print(userName);
            System.out.print(comm.name);
            if (ucdao.usercommunityexists(userName, comm.name) == false)
            {%>
            	
            	<form action="UserCommunity" method="post">
            	<input name="community" type="hidden" value=<%= comm.name%>>
		        <input type="submit" value="Join Community">
	       		</form>
            	
           <%}
           else
           {%>
        	    <form action="UserCommunity" method="post">
           		<input name="community" type="hidden" value=<%= comm.name%>>
		        <input type="submit" value="Leave Community">
	       		</form>
	       		
	       		<form action="./AddComment.jsp" method="post">
           		<input name="community" type="hidden" value=<%= comm.name%>>
		   		<input name="comment" type="text" >
		        <input type="submit" value="Add Comment">
	       		</form>
        	   
          <% }%>
            
            <div class="friends"><%
        	CommentDAO udao = new CommentDAO();
        	List<comment> uc = udao.findCommentsForCommunity(comm.name);%>
        	<p>Comments</p>
        	<table style="width:20%">
        	<% for (comment u: uc){%>
            <tr><td><% out.println(u.getContent());%></td></tr>
        	<%}
        	}%>   	
        	</table></div>
            </div>
</body>
</html>