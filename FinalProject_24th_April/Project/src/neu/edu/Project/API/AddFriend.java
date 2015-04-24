package neu.edu.Project.API;

import neu.edu.Project.DAO.*;
import neu.edu.Project.Entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	friendsDAO friendDAO = new friendsDAO();
    	UserDAO dao = new UserDAO();
    	
        // get request parameters for userID and password
        String targetUser = request.getParameter("hidden");
        //System.out.println(targetUser);
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
        
        if (userName.equals(targetUser)) {
        	ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=blue>User cannot add themself as friend</font>\n");
            rd.forward(request, response);
        }
        
        if (friendDAO.friendshipexists(userName, targetUser) == true) {
        	
        	friendDAO.remove(userName, targetUser);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Friend removed.</font>\n");
            rd.forward(request, response);
        } else {
        	friends f = new friends(userName, targetUser);
        	friendDAO.addfriend(f);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Friend Added.</font>\n");
            rd.include(request, response);
        }
 
    }
}