package servlets;

import org.amin.pcshop.domain.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** * Servlet implementation class LoginServlet */

/**
 * This servlet is used to control the Log in process of user to the 
 * Admin area of the application
 * 
 * 
 * @author Amin & Soode
 */


public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {


        HttpSession session = request.getSession();
        //String url = null;
        //session.setAttribute("currentUser", request.getRemoteUser());

        if (session.getAttribute("logedinUser") != null) {
            response.sendRedirect("admin.jsp");
        } else if(request.getParameter("action").equals("login")){
            response.sendRedirect("loginPage.jsp");
        } 
        else
        {
            try {
                Profile user = new Profile();

                user.setUser(request.getParameter("un"));
                user.setPassword(request.getParameter("pw"));

                user = Profile.loginAdmin(user);
                if (user.getValid()) {
                    //session = request.getSession(true);
                    session.setAttribute("logedinUser", user);
                    //url = "/admin.jsp";
                    
                    
//                    RequestDispatcher dispatch = 
//                            getServletContext().getRequestDispatcher(url);
//                    dispatch.forward(request, response);
                    response.sendRedirect("admin.jsp");
                } else {
                    
                    //url = "/login_error_admin.jsp";
//                    RequestDispatcher dispatch = 
//                            getServletContext().getRequestDispatcher(url);
//                    dispatch.forward(request, response);
                    response.sendRedirect("login_error_admin.jsp");
                }
//error page 
            } catch (Throwable theException) {
                System.out.println(theException);
            }
        }
    }
}