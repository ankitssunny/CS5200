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
    {
    	response.sendRedirect("login.html");
    }
    
    CommentDAO cdao = new CommentDAO();
	String communityName = request.getParameter("community");
	String comment = request.getParameter("comment");
	comment cmnt=new comment(null,userName,communityName,comment);
    cdao.createComment(cmnt);
   
    
	%>
<title><%= user.getUsername()%>'s Shopping Portal</title>
</head>
<body>

<h2> Thank You for adding your comment. </h2>
	
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
</body>
</html>