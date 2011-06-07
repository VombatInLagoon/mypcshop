package servlets;


import business.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** * Servlet implementation class LoginServlet */
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            ProfileBean user = new ProfileBean();

            user.setUser(request.getParameter("un"));
            user.setPassword(request.getParameter("pw"));
            
            user = ProfileBean.loginAdmin(user);
            if (user.getValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", user);

                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("login_error_admin.jsp");
            }
//error page 
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}