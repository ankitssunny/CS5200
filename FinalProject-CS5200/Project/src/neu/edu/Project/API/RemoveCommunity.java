package neu.edu.Project.API;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.Project.DAO.*;
import neu.edu.Project.Entity.*;

@SuppressWarnings("serial")
@WebServlet("/RemoveCommunity")
public class RemoveCommunity extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Getting Parameters from homepage.jsp page
    	
    	UserDAO dao = new UserDAO();
		
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("User")) {
                    userName = cookie.getValue();
                	@SuppressWarnings("unused")
					User usr = dao.findUser(userName);
                	break;
                }
            }
        }
        
        CommunityDAO cdao = new CommunityDAO();
 	 	String communityName = request.getParameter("community");
 	 	cdao.removeCommunity(communityName);	
    	response.sendRedirect("homepage.jsp");
    }
}