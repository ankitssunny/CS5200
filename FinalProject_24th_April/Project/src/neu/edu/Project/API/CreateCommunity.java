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

import neu.edu.Project.DAO.CommunityDAO;
import neu.edu.Project.DAO.UserCommunityDAO;
import neu.edu.Project.DAO.UserDAO;
import neu.edu.Project.Entity.Community;
import neu.edu.Project.Entity.User;
import neu.edu.Project.Entity.User_Communities;


@WebServlet("/CreateCommunity")
public class CreateCommunity extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	CommunityDAO cdao = new CommunityDAO();
    	UserDAO dao = new UserDAO();
    	//System.out.print("We are getting the register servlet.");
    	// Getting Parameters from login.jsp page
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");
        String userName = null;
        @SuppressWarnings("unused")
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
        
        // Checking if the Username exists in the database
        if(cdao.communityexists(cname)) {
        	RequestDispatcher r = getServletContext().getRequestDispatcher("/CreateCommunity.jsp");
            PrintWriter out = response.getWriter();
            out.println("<div align=\"center\" <font color=red>Community already exists. Enter new Community name</font></div>\n");
            r.include(request, response);
        }
        else {
        	Community comm = new Community(cname,userName, description);
        	cdao.createCommunity(comm);
        	UserCommunityDAO ucd = new UserCommunityDAO();
        	User_Communities uu = new User_Communities(userName, cname);
        	ucd.Join(uu);
            response.sendRedirect("homepage.jsp");
        }
    }
}