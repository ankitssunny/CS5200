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


@WebServlet("/ViewFriend")
public class ViewFriend extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        String friendname = request.getParameter("username");
    	//System.out.print("I am getting the Friend's name");
    	//System.out.print(userName);
    	HttpSession session = request.getSession();
    	session.setAttribute("name", friendname);
        response.sendRedirect("FriendList.jsp");
        
    
    }
}
    