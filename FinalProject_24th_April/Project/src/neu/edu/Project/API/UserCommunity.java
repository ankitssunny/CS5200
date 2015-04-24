package neu.edu.Project.API;

import neu.edu.Project.DAO.*;
import neu.edu.Project.Entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserCommunity")
public class UserCommunity extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	UserCommunityDAO ucdao = new UserCommunityDAO();
		UserDAO dao = new UserDAO();
    	String communityName = request.getParameter("community");
        System.out.println(communityName);
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
        
        if (ucdao.usercommunityexists(userName, communityName)== false) {
        	User_Communities uc = new User_Communities(userName,communityName);
        	ucdao.Join(uc);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>You have just Joined a community.</font>\n");
            rd.forward(request, response);
        }
        else{
        	 ucdao.unJoin(userName, communityName);
        	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
             PrintWriter out = response.getWriter();
             out.println("<font color=red>You have left a community.</font>\n");
             rd.include(request, response);
        	
        }
        	
 
    }
}