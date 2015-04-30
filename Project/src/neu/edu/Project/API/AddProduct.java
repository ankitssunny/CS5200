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
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	AddtoCartDAO pDAO = new AddtoCartDAO();
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
        
        if (pDAO.productxists(userName, title) == true) {
        	pDAO.removeproduct(userName, title);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Product removed from cart</font>\n");
            rd.forward(request, response);
        } else {
        	AddtoCart f = new AddtoCart(userName, title);
        	pDAO.createcartproduct(f);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>New Item added to cart</font>\n");
            rd.include(request, response);
        }
 
    }
}