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
import neu.edu.Project.Entity.*;

@SuppressWarnings("serial")
@WebServlet("/LikeProduct")
public class LikeProduct extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LikeDAO lDAO = new LikeDAO();
    	UserDAO dao = new UserDAO();
    	
        // get request parameters for userID and title
        String title = request.getParameter("title");
        String userName = null;
        User curUser = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("User")) {
                    userName = cookie.getValue();
                	curUser = dao.findUser(userName);
                	break;
                }
            }
        }
        
        if (userName == null || curUser == null) {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Time Out. Please login again</font>\n");
            rd.include(request, response);
        }
        
        if (lDAO.likeexists(userName, title) == true) {
        	lDAO.removelike(userName, title);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Product Unliked.</font>\n");
            rd.forward(request, response);
        } else {
        	Likes f = new Likes(userName, title);
        	lDAO.createLike(f);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>New Product Liked.</font>\n");
            rd.include(request, response);
        }
 
    }
}