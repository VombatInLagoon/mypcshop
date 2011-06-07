package servlets;


import business.*;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** * Servlet implementation class LoginServlet */
public class LoginProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            ProfileBean user = new ProfileBean();
            String userName = request.getParameter("un");
            HashMap<String, Boolean> roles = null;
            
            
            user.setUser(userName);
            user.setPassword(request.getParameter("pw"));
            
            user = ProfileBean.login(user);
            if (user.getValid()) {
                HttpSession session = request.getSession(true);
                
                user.populate(userName);
                roles=user.getRoles();
                
                session.setAttribute("roles", roles);
                session.setAttribute("currentUser", userName);
                session.setAttribute("profile", user);

                response.sendRedirect("profile.jsp");
            } else {
                response.sendRedirect("login_error_profile.jsp");
            }
//error page 
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}