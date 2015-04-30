package neu.edu.Project.API;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neu.edu.Project.DAO.*;
 
 
@SuppressWarnings("serial")
@WebServlet("/LoginVerify")
public class LoginVerify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	UserDAO dao = new UserDAO();
    	 
    	// Getting Parameters from login.jsp page
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Checking if the Username and Password combination exist in the database
        if(dao.verifyuser(username, password)) {
            Cookie Login = new Cookie("User", username);
            Login.setMaxAge(60 * 60 * 24);
            response.addCookie(Login);
            response.sendRedirect("homepage.jsp");
        } else {
            RequestDispatcher r = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<div align=\"center\" <font color=red>Username and/or password do not match</font></div>\n");
            r.include(request, response);
        }
    }
}