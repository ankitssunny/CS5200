package neu.edu.Project.API;

import neu.edu.Project.Entity.*;
import neu.edu.Project.DAO.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@SuppressWarnings("serial")
@WebServlet("/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	UserDAO dao = new UserDAO();
    	
    	//System.out.print("We are getting the register servlet.");
    	// Getting Parameters from login.jsp page
        String username = request.getParameter("newusername");
        String password = request.getParameter("newpassword");
        String address = request.getParameter("address");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String credit = request.getParameter("credit");
        
        System.out.print(username);
        // Checking if the Username exists in the database
        if(dao.userexists(username)) {
        	RequestDispatcher r = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<div align=\"center\" <font color=red>Username already exists. Enter new Username.</font></div>\n");
            r.include(request, response);
        }
        else {
        	User user = new User(username,password,address,fname,lname,contact,email,credit);
        	dao.createuser(user);
            Cookie Login = new Cookie("User", username);
            Login.setMaxAge(60 * 60 * 24);
            response.addCookie(Login);
            response.sendRedirect("homepage.jsp");
        }
    }
}