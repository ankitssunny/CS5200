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
<title>Ebay API Search Page</title>
</head>
<body>
<h1><%= user.getUsername()%>'s eBay Search Page</h1>

	<form method="post">
		<input type=text name="keyword"> <input type="Submit"
			name="btn" value="submit">
	</form>

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

<%
	String keyword = request.getParameter("keyword");
	EBayAPI client = new EBayAPI();
	if (keyword != null && keyword.length() > 1) 
	{
	List<Product> products = new ArrayList<Product>();
	products = client.getProductsforKeyword(keyword);
%>
	<table class="table">
		<tr>
			<td>Picture</td>
			<td>Title</td>
			<td>Category</td>
			<td>Price</td>
			<td>Like/UnLike Product</td>
		</tr>
		
		<%
       	LikeDAO ldao = new LikeDAO();
		System.out.println(products);
		if (products != null) {
			for (Product p : products) {%>
					<tr>
						<td><img src=<%=p.getGalleryURL()%>></td>
						<td><%=p.getTitle()%></td>
						<td><%=p.getPrimaryCategoryName()%></td>
						<td>$<%=p.getConvertedCurrentPrice()%></td>
						<td>
						<%
						if (ldao.likeexists(userName,p.getItemID()) == true) {%>
							<form action="LikeProduct" method="post">
							<input name="title" type="hidden" value=<%=p.getTitle()%>>
							<input type="Submit" name="btn" value="Unlike">
							</form>
						<%
						}else
						{%>	
							<form action="LikeProduct" method="post">
							<input name="title" type="hidden" value=<%=p.getTitle()%>>
							<input type="Submit" name="btn" value="Like">
							</form>
						<%}%>
						</td>
					</tr>	
			
	<% 		}
	   	}
	}%>
	</table>
</body>
</html>